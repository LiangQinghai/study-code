package cn.liangqinghai.study.log.client.kafka;

import cn.liangqinghai.study.log.client.BaseClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author LiangQinghai
 * @Title KafkaProducerClient
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 10:57
 */
public class KafkaProducerClient extends BaseClient {

    private static KafkaProducerClient instance;

    private KafkaProducerPool kafkaProducerPool;

    private KafkaProducerClient(String hosts) {
        this.kafkaProducerPool = new KafkaProducerPool(hosts);
    }

    public static KafkaProducerClient getInstance(String hosts) {
        if (instance == null) {
            synchronized (KafkaProducerClient.class) {
                if (instance == null) {
                    instance = new KafkaProducerClient(hosts);
                }
            }
        }
        return instance;
    }

    @Override
    @SuppressWarnings("all")
    public void pushMessage(String topic, String message) {

        KafkaProducer<?, ?> resource = kafkaProducerPool.getResource();
        resource.send(new ProducerRecord(topic, message));
        kafkaProducerPool.returnResource(resource);

    }

}
