package cn.liangqinghai.study.flink;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title StreamWithKafka
 * @ProjectName study-code
 * @Description
 * @date 3/27/2020 5:26 PM
 */
public class StreamWithKafka {

    private static Properties kafkaConfig;

    static {
        kafkaConfig = new Properties();
        InputStream inputStream = StreamWithKafka.class.getClassLoader().getResourceAsStream("kafka.properties");
        try {
            kafkaConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build();

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(kafkaConfig.getProperty("topic"), new SimpleStringSchema(), kafkaConfig);


        DataStream<Row> outputStreamOperator = env.addSource(consumer).flatMap(new FlatMapFunction<String, JSONObject>() {

            private static final long serialVersionUID = 6830806646980603159L;

            @Override
            public void flatMap(String value, Collector<JSONObject> out) throws Exception {
                out.collect(JSONUtil.parseObj(value));
            }
        })
                .map((MapFunction<JSONObject, Row>) (json) -> {
                    Object statDate = json.get("STAT_DATE");
                    Object appId = json.get("APP_ID");
                    Object tphone = json.get("TPHONE");

                    return Row.of(statDate, appId, tphone);
                })
                .returns(new RowTypeInfo(
                        new TypeInformation[]{Types.STRING, Types.STRING, Types.STRING},
                        new String[]{"STAT_DATE", "APP_ID", "TPHONE"}));

        tableEnv.createTemporaryView("TMP_1", outputStreamOperator, "STAT_DATE, APP_ID, TPHONE");

        Table table = tableEnv.sqlQuery("\n" +
                "SELECT\n" +
                "  STAT_DATE,\n" +
                "  APP_ID,\n" +
                "  COUNT(DISTINCT TPHONE) AS NEW_MEMBER\n" +
                "FROM\n" +
                "  `TMP_1`\n" +
                "GROUP BY STAT_DATE,\n" +
                "  APP_ID");

        DataStream<Tuple2<Boolean, Row>> rowDataStream = tableEnv.toRetractStream(table, Row.class);

        rowDataStream.print().setParallelism(1);

        env.execute("lqh");

    }

}
