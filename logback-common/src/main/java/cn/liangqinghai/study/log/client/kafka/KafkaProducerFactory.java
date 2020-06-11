package cn.liangqinghai.study.log.client.kafka;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title KafkaProducerFactory
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 11:27
 */
public class KafkaProducerFactory implements PooledObjectFactory<KafkaProducer<?, ?>> {

    private Properties prop = new Properties();

    KafkaProducerFactory(String hosts) {

        prop.put("acks", "0");
        prop.put("bootstrap.servers", hosts);
        prop.put("key.serializer", StringSerializer.class);
        prop.put("value.serializer", StringSerializer.class);
        prop.put("linger.ms", 10);

    }

    @Override
    public PooledObject<KafkaProducer<?, ?>> makeObject() throws Exception {

        KafkaProducer<?, ?> producer = new KafkaProducer<>(prop);

        return new DefaultPooledObject<>(producer);
    }

    @Override
    public void destroyObject(PooledObject<KafkaProducer<?, ?>> pooledObject) throws Exception {
        pooledObject.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<KafkaProducer<?, ?>> pooledObject) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<KafkaProducer<?, ?>> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<KafkaProducer<?, ?>> pooledObject) throws Exception {

    }
}
