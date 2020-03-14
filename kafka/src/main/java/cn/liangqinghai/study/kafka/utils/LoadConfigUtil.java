package cn.liangqinghai.study.kafka.utils;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title LoadConfigUtil
 * @ProjectName study-code
 * @Description
 * @date 2020/3/14 14:19
 */
public final class LoadConfigUtil {

    private static Properties kafkaConfig;

    static {
        load();
    }

    private static Properties load() {
        kafkaConfig = new Properties();
        try {
            kafkaConfig.load(LoadConfigUtil.class.getClassLoader().getResourceAsStream("kafka.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kafkaConfig;
    }


    /**
     * consumer config
     *
     * @return
     */
    public static Properties getKafkaConfigForConsumer() {
        Properties config = getKafkaConfigForProducer();
        config.setProperty("group.id", "lqh_test_consumer_group");
        config.setProperty("enable.auto.commit", "true");
        config.setProperty("auto.commit.interval.ms", "1000");
        config.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return config;
    }

    /**
     * producer config
     *
     * @return
     */
    public static Properties getKafkaConfigForProducer() {
        if (kafkaConfig != null && kafkaConfig.size() > 0 ){
            return kafkaConfig;
        } else {
            return load();
        }
    }

    /**
     * stream config
     * @return
     */
    public static Properties getKafkaConfigForStream() {
        Properties config = getKafkaConfigForProducer();
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "world-count");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return config;
    }

}
