package cn.liangqinghai.study.flink.hive;

import com.google.gson.Gson;
import lombok.Data;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connectors.hive.HiveTableFactory;
import org.apache.flink.connectors.hive.HiveTableSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.CatalogTableImpl;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.TableNotExistException;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.table.catalog.hive.client.HiveShimLoader;
import org.apache.flink.table.factories.TableFactory;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.types.Row;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Table;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiangQinghai
 * @Title StreamInsertData
 * @ProjectName study-code
 * @Description
 * @date 2020/2/25 16:24
 */
public class StreamInsertData {

    public static void main(String[] args) throws TableNotExistException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "hd-node-3-41.wakedata.com:6667,hd-node-3-42.wakedata.com:6667,hd-node-3-43.wakedata.com:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        FlinkKafkaConsumer010<String> kafkaConsumer = new FlinkKafkaConsumer010<>("One", new SimpleStringSchema(), props);

        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tbEnv = StreamTableEnvironment.create(env, settings);

        //


        DataStreamSource<String> dataStreamSource = env.addSource(kafkaConsumer);
        dataStreamSource.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {
                Gson gson = new Gson();
                Person person = gson.fromJson(value, Person.class);
                Row row = new Row(5);
                row.setField(0, person.id);
                row.setField(1, person.name);
                row.setField(2, person.age);
                row.setField(3, person.proctime);
                row.setField(4, 20200225L);
                return null;
            }
        });

    }

    public static HiveTableSink getHiveSink() throws TableNotExistException {

        HiveCatalog hiveCatalog = new HiveCatalog("test", "wakedata3", "/etc/hadoop/conf", HiveShimLoader.getHiveVersion());
        hiveCatalog.open();
        Optional<TableFactory> factoryOptional = hiveCatalog.getTableFactory();

        if (factoryOptional.isPresent()) {

            HiveTableFactory hiveTableFactory = (HiveTableFactory) factoryOptional.get();
            Table simTable = hiveCatalog.getHiveTable(new ObjectPath("wakedata3", "lqh_msyql_sim"));

//            List<FieldSchema> fieldSchemas = simTable.getSd().getCols();
//            fieldSchemas.forEach(fieldSchema -> {
//            });
            TableSchema build = TableSchema.builder().field("id", DataTypes.INT())
                    .field("name", DataTypes.STRING())
                    .field("age", DataTypes.INT())
                    .field("proctime", DataTypes.TIMESTAMP())
                    .field("stat_date", DataTypes.BIGINT())
                    .build();
            List<String> collect = simTable.getPartitionKeys().stream().map(FieldSchema::getName).collect(Collectors.toList());
            Map<String, String> prop = new HashMap<>();
            prop.put("transient_lastDdlTime", "1575614075");
            prop.put("bucketing_version", "2");
            prop.put("EXTERNAL", "TRUE");
            prop.put("is_generic", "false");
            CatalogTableImpl catalogTable = new CatalogTableImpl(build, collect, prop, "");

            return (HiveTableSink) hiveTableFactory.createTableSink(new ObjectPath("wakedata3", "lqh_msyql_sim"), catalogTable);

        }

        return null;
    }

    @Data
    static class Person {

        private int id;
        private String name;
        private int age;
        private LocalDate proctime;

    }

    static class JsonDeserialization extends AbstractDeserializationSchema<Row> {
        @Override
        public Row deserialize(byte[] message) throws IOException {
            return null;
        }
    }

}
