package cn.liangqinghai.study.flink;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build();

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(kafkaConfig.getProperty("topic"), new SimpleStringSchema(), kafkaConfig);

        tableEnv.fromDataStream(env.addSource(consumer), "id, idnn");

    }

    @Data
    @Builder
    public static class EntityBody implements Serializable {

        private static final long serialVersionUID = 6620750767920100034L;

        private List<String> fieldNames;

    }

    public static class JsonObjectSchema implements DeserializationSchema<Row> {

        private static final long serialVersionUID = 6722325048403322950L;

        @Override
        public Row deserialize(byte[] message) throws IOException {

            JSONObject jsonObject = JSONUtil.parseObj(new String(message, StandardCharsets.UTF_8));

            return null;
        }

        @Override
        public boolean isEndOfStream(Row nextElement) {
            return false;
        }

        @Override
        public TypeInformation<Row> getProducedType() {
            return null;
        }
    }

}
