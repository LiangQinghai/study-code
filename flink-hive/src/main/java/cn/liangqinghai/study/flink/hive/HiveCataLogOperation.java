package cn.liangqinghai.study.flink.hive;

import com.google.gson.Gson;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.catalog.CatalogPartitionSpec;
import org.apache.flink.table.catalog.CatalogTableImpl;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.apache.flink.table.catalog.exceptions.TableAlreadyExistException;
import org.apache.flink.table.catalog.exceptions.TableNotExistException;
import org.apache.flink.table.catalog.exceptions.TableNotPartitionedException;
import org.apache.flink.table.catalog.hive.HiveCatalog;
import org.apache.flink.table.catalog.hive.client.HiveShimLoader;
import org.apache.hadoop.hive.metastore.api.Table;

import java.util.HashMap;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title HiveCataLogOperation
 * @ProjectName study-code
 * @Description
 * @date 2020/2/29 16:26
 */
public class HiveCataLogOperation {

    public static void main(String[] args) throws TableNotExistException, TableAlreadyExistException, DatabaseNotExistException, TableNotPartitionedException {
        HiveCatalog hiveCatalog = new HiveCatalog("test", "wakedata3", "/etc/hadoop/conf", HiveShimLoader.getHiveVersion());
        hiveCatalog.open();

        Table hiveTable = hiveCatalog.getHiveTable(new ObjectPath("wakedata3", "lqh_multi_partion_five"));

        Gson gson = new Gson();
        String s = gson.toJson(hiveTable);
        System.out.println(s);

        List<CatalogPartitionSpec> catalogPartitionSpecs = hiveCatalog.listPartitions(new ObjectPath("wakedata3", "lqh_multi_partion_five"));

        System.out.println(gson.toJson(catalogPartitionSpecs));


        TableSchema build = TableSchema.builder().field("id", DataTypes.INT())
                .field("name", DataTypes.STRING())
                .build();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("location", "hdfs://HDFSCluster/warehouse/tablespace/external/hive/wakedata3.db/lqh_multi_partion_five");
        properties.put("inputFormat", "org.apache.hadoop.hive.ql.io.orc.OrcInputFormat");
        properties.put("outputFormat", "org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat");
        properties.put("compressed", "false");
        properties.put("tableType", "EXTERNAL_TABLE");
        CatalogTableImpl table = new CatalogTableImpl(build, properties, "");
        hiveCatalog.createTable(new ObjectPath("wakedata3", "lqh_hhhh"), table, true);

        hiveCatalog.close();
    }

}
