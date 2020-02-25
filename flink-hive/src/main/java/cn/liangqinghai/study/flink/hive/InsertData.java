package cn.liangqinghai.study.flink.hive;

import org.apache.flink.connectors.hive.HiveTableFactory;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.catalog.CatalogDatabaseImpl;
import org.apache.flink.table.catalog.CatalogTableBuilder;
import org.apache.flink.table.catalog.CatalogTableImpl;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.config.CatalogConfig;
import org.apache.flink.table.catalog.exceptions.DatabaseAlreadyExistException;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.apache.flink.table.catalog.exceptions.TableAlreadyExistException;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.table.catalog.hive.HiveCatalogConfig;
import org.apache.flink.table.catalog.hive.client.HiveShimLoader;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.types.Row;
import org.apache.hadoop.hive.conf.HiveConf;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author LiangQinghai
 * @Title InserData
 * @ProjectName study-code
 * @Description
 * @date 2020/2/25 10:29
 */
public class InsertData {

    public static void main(String[] args) throws DatabaseAlreadyExistException, TableAlreadyExistException, DatabaseNotExistException {

        HiveCatalog hiveCatalog = new HiveCatalog("test", "default", "/etc/hadoop/conf", HiveShimLoader.getHiveVersion());
        HiveConf hiveConf = hiveCatalog.getHiveConf();
        hiveConf.setVar(HiveConf.ConfVars.HIVEDEFAULTFILEFORMAT, "ORC");

        TableSchema schema = TableSchema.builder()
                .field("id", DataTypes.INT())
                .field("name", DataTypes.STRING())
                .field("age", DataTypes.INT())
                .build();
        hiveCatalog.open();
        hiveCatalog.createDatabase("streaming", new CatalogDatabaseImpl(new HashMap<>(), ""), true);
        ObjectPath path = new ObjectPath("streaming", "lqh_hive_01");
        HashMap<String, String> properties = new HashMap<>();
        properties.put("is_streaming", "false");
        properties.put(CatalogConfig.IS_GENERIC, "false");
        CatalogTableImpl table = new CatalogTableImpl(schema, properties, "lqh test flink 1.10 hive sink");
        hiveCatalog.createTable(path, table, true);
        HiveTableFactory tableFactory = (HiveTableFactory) hiveCatalog.getTableFactory().get();

        TableSink<Row> tableSink = tableFactory.createTableSink(path, table);

        System.out.println(Arrays.toString(tableSink.getFieldNames()));
    }

}
