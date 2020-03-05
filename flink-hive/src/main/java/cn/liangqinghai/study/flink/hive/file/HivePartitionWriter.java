package cn.liangqinghai.study.flink.hive.file;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.catalog.CatalogPartitionImpl;
import org.apache.flink.table.catalog.CatalogPartitionSpec;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.PartitionAlreadyExistsException;
import org.apache.flink.table.catalog.exceptions.PartitionSpecInvalidException;
import org.apache.flink.table.catalog.exceptions.TableNotExistException;
import org.apache.flink.table.catalog.exceptions.TableNotPartitionedException;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.table.catalog.hive.client.HiveShimLoader;
import org.apache.flink.types.Row;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.ql.io.orc.CompressionKind;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
import org.apache.hadoop.hive.ql.io.orc.Writer;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.SettableStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableLongObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LiangQinghai
 * @Title HiveMain
 * @ProjectName my-flink-project
 * @Description
 * @date 2020/2/28 10:55
 */
public class HivePartitionWriter {

    private static final String confPath = "/etc/hadoop/conf";

    private static final String tableName = "lqh_multi_partion_five";

    private static final Configuration hadoopConf = new Configuration(false);

    private static final String path_prefix = "hdfs://HDFSCluster/warehouse/tablespace/external/hive/wakedata3.db/" + tableName +"/";

    private static volatile HiveCatalog hiveCatalog;

    private static volatile CopyOnWriteArraySet<String> partionCache;

    static {
        hadoopConf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
        hadoopConf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
        hadoopConf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        hadoopConf.set("fs.hdfs.impl.disable.cache", "true");

        hiveCatalog = new HiveCatalog("test", "wakedata3", confPath, HiveShimLoader.getHiveVersion());

        partionCache = new CopyOnWriteArraySet<>();

    }

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build();

        TableEnvironment tabEnv = TableEnvironment.create(settings);

        DataStreamSource<String> kafkaStream = getKafkaStream(env);

        kafkaStream.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {

                String[] split = value.split(",");

                Row row = new Row(4);

                // area
                row.setField(0, Integer.parseInt(split[0]));
                //age
                row.setField(1, Integer.parseInt(split[1]));
                //id
                row.setField(2, Integer.parseInt(split[2]));
                // name
                row.setField(3, split[3]);

                return row;
            }
        })
                .addSink(new HiveSink())
//                .addSink(new PrintSinkFunction<>())
                .setParallelism(1);

        env.execute("----");

    }

    private static DataStreamSource<String> getKafkaStream(StreamExecutionEnvironment env) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hd-node-3-41.wakedata.com:6667,hd-node-3-42.wakedata.com:6667,hd-node-3-43.wakedata.com:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        FlinkKafkaConsumer010<String> consumer010 = new FlinkKafkaConsumer010<>("one", new SimpleStringSchema(), props);

        return env.addSource(consumer010);

    }


    private static class HiveSink extends RichSinkFunction<Row> {

        private ConcurrentHashMap<String, HiveFileWriter> fileWriters;

        private ConcurrentHashMap<String, Path> paths;

        private ReentrantReadWriteLock.ReadLock readLock;

        private ReentrantReadWriteLock.WriteLock writeLock;

        @Override
        public void open(org.apache.flink.configuration.Configuration parameters) throws Exception {
            super.open(parameters);

            this.fileWriters = new ConcurrentHashMap<>();
            this.paths = new ConcurrentHashMap<>();

            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            readLock = lock.readLock();
            writeLock = lock.writeLock();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));

            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
            executor.scheduleAtFixedRate(() -> {

                this.fileWriters.forEach((k, v) -> {
                    long now = System.currentTimeMillis();
                    if (now - v.expire > 60 * 10 * 1000) {
                        try {
                            writeLock.lock();
                            v.closeWriter();
                            this.paths.remove(k);
                            this.fileWriters.remove(k);
                            System.out.println("Remove: " + k);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            writeLock.unlock();
                            System.out.println("Size Of writer map: " + this.fileWriters.size());
                        }
                    }
                });

            }, 2, 2, TimeUnit.MINUTES);


            hiveCatalog.open();

            List<CatalogPartitionSpec> partitions = hiveCatalog.listPartitions(new ObjectPath("wakedata3", tableName));

            partitions.forEach(catalogPartitionSpec -> {

                Map<String, String> partitionSpec = catalogPartitionSpec.getPartitionSpec();

                StringBuilder sb = new StringBuilder();
                partitionSpec.forEach((k, v) -> {
                    sb.append(k).append("=").append(v);
                });

                partionCache.add(sb.toString());

            });


            Table hiveTable = hiveCatalog.getHiveTable(new ObjectPath("wakedata3", "lqh_multi_partion_five"));

            Gson gson = new Gson();
            String s = gson.toJson(hiveTable);
            System.out.println(s);

            System.out.println("---Open---");

        }

        private Path getPath(Map<String, Object> kv) {
            StringBuilder sb = new StringBuilder(path_prefix);
            kv.forEach((k, v) -> sb.append(k).append("=").append(v).append("/"));
            sb.append(System.currentTimeMillis()).append(RandomUtils.nextInt(0, 9));
            return new Path(sb.toString());
        }

        @Override
        public void invoke(Row value, Context context) throws Exception {

            try {

                readLock.lock();


                Map<String, Object> keys = new HashMap<>();
                keys.put("are", value.getField(0));
                keys.put("age", value.getField(1));
                createPartitionIfNotExits(keys);

                String key = generateKey(keys);
                HiveFileWriter hiveFileWriter = this.fileWriters.get(key);
                if (hiveFileWriter == null) {
                    Path path = this.paths.get(key);
                    if (path == null) {
                        path = getPath(keys);
                    }
                    this.paths.put(key, path);
                    synchronized (this) {
                        hiveFileWriter = new HiveFileWriter();
                        hiveFileWriter.open(path);
                    }
                } else {
                    System.out.println("Writer cache hint: " + key + "  , cache size: " + this.fileWriters.size());
                }

                this.fileWriters.put(key, hiveFileWriter);

                hiveFileWriter.write(Row.of(value.getField(2), value.getField(3)));

            } catch (IOException | InterruptedException e) {
                throw new Exception(e);
            } finally {

                readLock.unlock();

            }

        }

        private void createPartitionIfNotExits(Map<String, Object> keys) throws TableNotPartitionedException, PartitionAlreadyExistsException, TableNotExistException, PartitionSpecInvalidException {

            StringBuilder sb = new StringBuilder();
            keys.forEach((k, v) -> {
                sb.append(k).append("=").append(v);
            });

            if (!partionCache.contains(sb.toString())) {
                Map<String, String> properties = new HashMap<>();
                keys.forEach((k, v) -> properties.put(k, String.valueOf(v)));
                CatalogPartitionSpec partitionSpec = new CatalogPartitionSpec(properties);
                CatalogPartitionImpl partition = new CatalogPartitionImpl(properties, "");
                hiveCatalog.createPartition(new ObjectPath("wakedata3", tableName), partitionSpec, partition, true);
            }

        }

        private String generateKey(Map<String, Object> keys) {
            StringBuilder sb = new StringBuilder();
            keys.forEach((k, v) -> {
                sb.append(k).append(":").append(v.toString()).append("-");
            });
            return sb.toString();
        }

        @Override
        public void close() throws Exception {
            super.close();
            fileWriters.forEach((k, v) -> {
                try {
                    v.closeWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            if (hiveCatalog != null) {
                hiveCatalog.close();
            }

        }
    }

    private static class HiveFileWriter{

        private SettableStructObjectInspector oi;
        private Writer orcFileWriter;
        private long expire;


        public void open(Path path) throws IOException, InterruptedException {
            this.expire = System.currentTimeMillis();
            if (this.orcFileWriter != null) {
                return;
            }
            FileSystem fileSystem = FileSystem.get(path.toUri(), hadoopConf, "hive");
            OrcFile.WriterOptions writerOptions = OrcFile.writerOptions(hadoopConf);
            TypeInfo typeInfo = TypeInfoUtils.getTypeInfoFromTypeString("struct<id:int,name:string>");
            StructObjectInspector inspector = (StructObjectInspector) OrcStruct.createObjectInspector(typeInfo);
            this.oi = (SettableStructObjectInspector) inspector;
            writerOptions.fileSystem(fileSystem).blockSize(8 * 1024 * 1024).compress(CompressionKind.SNAPPY).inspector(inspector);
            this.orcFileWriter = OrcFile.createWriter(path, writerOptions);
        }

        public synchronized boolean write(Row row) throws IOException {
            OrcStruct orcLine = (OrcStruct) oi.create();
            for (int i = 0; i < row.getArity(); i++) {
                Object writableVal = null;
                String val = String.valueOf(row.getField(i));
                StructField structField = oi.getAllStructFieldRefs().get(i);
                if (structField.getFieldObjectInspector() instanceof WritableLongObjectInspector) {
                    Long longVal = null;
                    if (val != null) {
                        longVal = Long.parseLong(val);
                        LongWritable longWritable = new LongWritable();
                        longWritable.set(longVal);
                        writableVal = longWritable;
                    }
                } else if (structField.getFieldObjectInspector() instanceof WritableDoubleObjectInspector) {
                    Double doubleVal = null;
                    if (val != null) {
                        doubleVal = Double.parseDouble(val);
                        DoubleWritable doubleWritable = new DoubleWritable();
                        doubleWritable.set(doubleVal);
                        writableVal = doubleWritable;
                    }
                } else if (structField.getFieldObjectInspector() instanceof WritableIntObjectInspector) {
                    Integer intVal = null;
                    if (val != null) {
                        intVal = Integer.parseInt(val);
                        IntWritable intWritable = new IntWritable();
                        intWritable.set(intVal);
                        writableVal = intWritable;
                    }
                }  else {
                    if (val != null) {
                        Text text = new Text();
                        text.set(val);
                        writableVal = text;
                    }
                }
                oi.setStructFieldData(orcLine, structField, writableVal);
            }
            //将数据写入ORCfile
            orcFileWriter.addRow(orcLine);
            return true;
        }

        public void closeWriter() throws IOException {
            synchronized (this) {
                if (orcFileWriter != null) {
                    try {
                        orcFileWriter.close();
                    } finally {
                        orcFileWriter = null;
                    }
                }
            }
        }

    }



}
