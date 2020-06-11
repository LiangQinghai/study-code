package cn.liangqinghai.study.log.client.kafka;

import cn.liangqinghai.study.log.client.Pool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author LiangQinghai
 * @Title KakfakProducerPool
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 11:23
 */
public class KafkaProducerPool extends Pool<KafkaProducer<?, ?>> {

    public KafkaProducerPool(GenericObjectPoolConfig<KafkaProducer<?, ?>> config, String hosts) {
        super(config, new KafkaProducerFactory(hosts));
    }

    public KafkaProducerPool(String hosts) {
        super(new GenericObjectPoolConfig<KafkaProducer<?, ?>>(), new KafkaProducerFactory(hosts));
    }


    @Override
    public KafkaProducer<?, ?> getResource() {
        return super.getResource();
    }
}