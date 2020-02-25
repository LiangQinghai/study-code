package cn.liangqinghai.study.flink.hive.utils;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.SqlDialect;
import org.apache.flink.table.api.TableEnvironment;

import static org.apache.flink.table.api.config.ExecutionConfigOptions.TABLE_EXEC_RESOURCE_DEFAULT_PARALLELISM;

/**
 * @author LiangQinghai
 * @Title FlinkEnviornmentUtil
 * @ProjectName study-code
 * @Description
 * @date 2020/2/25 10:30
 */
public class FlinkEnviornmentUtil {

    /**
     *
     * @return
     */
    public static TableEnvironment getTableEnvironment() {
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inBatchMode().build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);
        tableEnv.getConfig().getConfiguration().setInteger(TABLE_EXEC_RESOURCE_DEFAULT_PARALLELISM.key(), 1);
        tableEnv.getConfig().setSqlDialect(SqlDialect.HIVE);
        return tableEnv;
    }

}
