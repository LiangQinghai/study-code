package cn.liangqinghai.study.kafka;

import cn.liangqinghai.study.kafka.utils.LoadConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author LiangQinghai
 * @Title Consumer
 * @ProjectName study-code
 * @Description
 * @date 2020/3/14 15:43
 */
public class Consumer {

    private static final String TOPIC = "lqh_test_embeded_json";

    public static void main(String[] args) {

        Properties config = LoadConfigUtil.getKafkaConfigForConsumer();

//        config.setProperty("enable.auto.commit", "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);

//        TopicPartition topicPartition = new TopicPartition(TOPIC, 0);
//        // 手动指定消费位置，无法自动负载均衡，所以要手动注册，才能消费
//        consumer.assign(Collections.singletonList(topicPartition));
//        consumer.seek(topicPartition, 1);


        List<PartitionInfo> infos = consumer.partitionsFor(TOPIC);
        List<TopicPartition> list = infos.stream().map(partitionInfo -> new TopicPartition(TOPIC, partitionInfo.partition())).collect(Collectors.toList());
        consumer.assign(list);
        consumer.seekToBeginning(list);

        boolean flag = true;

        while (flag) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record ->
                    System.out.println(String.format("partition: %d, offset: %d, key: %s, value: %s, timestamp: %d",
                            record.partition(), record.offset(), record.key(), record.value(), record.timestamp())));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));

    }

}
