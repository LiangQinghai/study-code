package cn.liangqinghai.study.flink.hive;

import cn.liangqinghai.study.flink.hive.utils.FlinkEnviornmentUtil;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.io.InputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.CollectionInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.table.catalog.hive.client.HiveShimLoader;
import org.apache.flink.table.sources.InputFormatTableSource;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.utils.TypeConversions;
import org.apache.flink.types.Row;
import org.apache.hadoop.hive.conf.HiveConf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title InserDataWithSql
 * @ProjectName study-code
 * @Description
 * @date 2020/2/25 14:37
 */
public class InsertDataWithSql {

    public static void main(String[] args) throws Exception {

        System.setProperty("HADOOP_USER_NAME", "hive");

        TableEnvironment tableEnv = FlinkEnviornmentUtil.getTableEnvironment();
        HiveCatalog hiveCatalog = new HiveCatalog("test", "wakedata3", "/etc/hadoop/conf", HiveShimLoader.getHiveVersion());

        TableSchema tableSchema = TableSchema.builder()
                .field("id", DataTypes.INT())
                .field("name", DataTypes.STRING())
                .field("age", DataTypes.INT())
                .field("proctime", DataTypes.TIMESTAMP())
                .field("stat_date", DataTypes.BIGINT())
                .build();

        RowTypeInfo rowTypeInfo = new RowTypeInfo(tableSchema.getFieldTypes(), tableSchema.getFieldNames());

        hiveCatalog.open();
        org.apache.hadoop.hive.metastore.api.Table hiveTable = hiveCatalog.getHiveTable(new ObjectPath("wakedata3", "lqh_msyql_sim"));
        System.out.println(hiveTable);
        HiveConf hiveConf = hiveCatalog.getHiveConf();
        hiveConf.setVar(HiveConf.ConfVars.BYTESPERREDUCER, "8 * 1024 * 1024");

        List<Row> res = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Row row = new Row(tableSchema.getFieldNames().length);
            row.setField(0, i);
            row.setField(1, "name_" + i);
            row.setField(2, i + 20);
            row.setField(3, LocalDateTime.now());
            row.setField(4, 20191206L);
            res.add(row);
        }

        tableEnv.registerCatalog("hive", hiveCatalog);

        Table src = tableEnv.fromTableSource(new CollectionTableSource(res, rowTypeInfo));
        tableEnv.registerTable("src", src);
        tableEnv.sqlQuery("select * from src").insertInto("hive.`wakedata3`.lqh_msyql_sim");

        tableEnv.execute("hive");

        hiveCatalog.close();
    }


    private static class CollectionTableSource extends InputFormatTableSource<Row> {

        private final Collection<Row> data;
        private final RowTypeInfo rowTypeInfo;

        CollectionTableSource(Collection<Row> data, RowTypeInfo rowTypeInfo) {
            this.data = data;
            this.rowTypeInfo = rowTypeInfo;
        }

        @Override
        public DataType getProducedDataType() {
            return TypeConversions.fromLegacyInfoToDataType(rowTypeInfo);
        }

        @Override
        public TypeInformation<Row> getReturnType() {
            return rowTypeInfo;
        }

        @Override
        public InputFormat<Row, ?> getInputFormat() {
            return new CollectionInputFormat<>(data, rowTypeInfo.createSerializer(new ExecutionConfig()));
        }

        @Override
        public TableSchema getTableSchema() {
            return new TableSchema.Builder().fields(rowTypeInfo.getFieldNames(),
                    TypeConversions.fromLegacyInfoToDataType(rowTypeInfo.getFieldTypes())).build();
        }
    }

}
