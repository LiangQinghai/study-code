package cn.liangqinghai.study.bd.hive;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.*;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.orc.TypeDescription;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title HiveOrcReader
 * @ProjectName study-code
 * @Description
 * @date 2020/5/8 19:41
 */
public class HiveOrcReader {

    public static void main(String[] args) throws IOException {

        Configuration configuration = new Configuration();
        Path path = new Path("C:\\Users\\WakeData\\Downloads\\000000_0");
        Reader reader = OrcFile.createReader(FileSystem.getLocal(configuration), path);

        TypeDescription schema = reader.getSchema();

        System.out.println(schema.toJson());

        StructObjectInspector inspector = (StructObjectInspector) reader.getObjectInspector();

        RecordReader rows = reader.rows();

        Object row = null;

        long count = 0L;

        while (rows.hasNext()) {
            row = rows.next(row);
            count ++;
            List<Object> dataAsList = inspector.getStructFieldsDataAsList(row);
            System.out.println(Arrays.toString(dataAsList.toArray(new Object[0])));
        }

    }

}
