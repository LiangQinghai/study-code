package cn.liangqinghai.study.bd.hive;

import cn.hutool.core.io.resource.ClassPathResource;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.RCFile;
import org.apache.hadoop.hive.ql.io.RCFileRecordReader;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.ColumnProjectionUtils;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeUtils;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.columnar.BytesRefWritable;
import org.apache.hadoop.hive.serde2.columnar.ColumnarSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title HiveRcFileReader
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 15:53
 */
public class HiveRcFileReader {

    private static Properties createProperties() {
        Properties tbl = new Properties();

        // Set the configuration parameters
        tbl.setProperty(serdeConstants.SERIALIZATION_FORMAT, "9");
        tbl.setProperty("columns",
                "abyte,ashort,aint,along,adouble,astring,anullint,anullstring");
        tbl.setProperty("columns.types",
                "tinyint:smallint:int:bigint:double:string:int:string");
        tbl.setProperty(serdeConstants.SERIALIZATION_NULL_FORMAT, "NULL");
        return tbl;
    }

    public static void main(String[] args) throws IOException, SerDeException {

        Configuration configuration = new Configuration();
//        Path path = new Path(new ClassPathResource("/file/000000_0.rc").getPath());
        Path path = new Path("D:\\WorkSpace\\IDEA\\study-code\\hdfs-study\\src\\main\\resources\\file\\000000_0");
//        ColumnProjectionUtils.setFullyReadColumns(configuration);
        FileSystem fileSystem = FileSystem.get(configuration);

        RCFile.Reader reader = new RCFile.Reader(fileSystem, path, configuration);

        LongWritable rowId = new LongWritable();
        int actualRead = 0;
        BytesRefArrayWritable cols = new BytesRefArrayWritable();

        ColumnarSerDe serDe = new ColumnarSerDe();
        SerDeUtils.initializeSerDe(serDe, configuration, createProperties(), null);
        while (reader.next(rowId)) {
            reader.getCurrentRow(cols);
            cols.resetValid(8);
            Object row = serDe.deserialize(cols);

            StructObjectInspector oi = (StructObjectInspector) serDe.getObjectInspector();

            List<? extends StructField> fieldRefs = oi.getAllStructFieldRefs();

            for (int i = 0; i < fieldRefs.size(); i++) {

                Object fieldData = oi.getStructFieldData(row, fieldRefs.get(i));

                Object standardWritableData = ObjectInspectorUtils
                        .copyToStandardObject(fieldData, fieldRefs.get(i).getFieldObjectInspector(), ObjectInspectorUtils.ObjectInspectorCopyOption.WRITABLE);

                System.out.println(standardWritableData);
            }

            BytesRefArrayWritable serializedText = (BytesRefArrayWritable) serDe
                    .serialize(row, oi);

            System.out.println(serializedText);

            actualRead ++;

        }

//        long len = fileSystem.getFileStatus(path).getLen();
//        FileSplit fileSplit = new FileSplit(path, 0, len, new JobConf(configuration));
//        RCFileRecordReader reader = new RCFileRecordReader(configuration, fileSplit);
//
//        LongWritable longWritable = new LongWritable();
//
//        BytesRefArrayWritable arrayWritable = new BytesRefArrayWritable();
//        Text text = new Text();
//        StringBuilder res = null;
//        List<String> resList = new ArrayList<>();
//
//        int y = 0;
//        while (reader.next(longWritable, arrayWritable)) {
//
//            res = new StringBuilder();
//
//            text.clear();
//
//            for (int i = 0; i < arrayWritable.size(); i++) {
//
//                BytesRefWritable v = arrayWritable.get(i);
//
//                text.set(v.getData(), v.getStart(), v.getLength());
//
//                res.append(text.toString());
//
//                if (i < arrayWritable.size()) {
//                    res.append("\t");
//                }
//
//                res.append("\n");
//                resList.add(res.toString());
//
//                y ++;
//
//            }
//
//        }
//
//        System.out.println(Arrays.toString(resList.toArray()));

//        RCFile.Reader reader = new RCFile.Reader(fileSystem, path, configuration);
//
//        int counter = 1;
//        while (reader.next(new LongWritable(counter))) {
//            BytesRefArrayWritable arrayWritable = new BytesRefArrayWritable();
//            reader.getCurrentRow(arrayWritable);
//            for (int i = 0; i < arrayWritable.size(); i++) {
//
//                BytesRefWritable bytesRefWritable = arrayWritable.get(i);
//
//                byte[] data = bytesRefWritable.getData();
//                Text text = new Text(data);
//                System.out.println(text.toString());
//
//            }
//            counter ++;
//        }

//        BytesRefArrayWritable arrayWritable = new BytesRefArrayWritable();
//
//        while (reader.nextBlock()) {
//
//            for (int count = 0; count < 2; count ++) {
//
//                arrayWritable = reader.getColumn(count, arrayWritable);
//
//                BytesRefWritable brw = null;
//
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < arrayWritable.size(); i++) {
//
//                    brw = arrayWritable.get(i);
//
//                    sb.append(Bytes.toString(brw.getData(), brw.getStart(), brw.getLength()));
//
//                    if (i < arrayWritable.size() - 1) {
//                        sb.append("\t");
//                    }
//
//                }
//
//                System.out.println(sb.toString());
//
//            }
//
//        }

        reader.close();

    }

}
