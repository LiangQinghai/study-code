package cn.liangqinghai.study.kafka;

import cn.liangqinghai.study.kafka.utils.LoadConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title Producer
 * @ProjectName study-code
 * @Description
 * @date 2020/3/14 14:52
 */
public class Producer {

    private static final String TOPIC = "lqh_test";

    public static void main(String[] args) {

        Properties kafkaConfig = LoadConfigUtil.getKafkaConfigForProducer();

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaConfig);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>(TOPIC, 1, null, "Value: " + i), ((metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                }
                System.out.println(String.format("Offset: %d, Partition: %d, Topic: %s, Timestamp: %d", metadata.offset(), metadata.partition(), metadata.topic(), metadata.timestamp()));
            }));
        }

        producer.flush();
        producer.close();

    }

}
