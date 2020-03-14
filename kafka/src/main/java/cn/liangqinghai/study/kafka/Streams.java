package cn.liangqinghai.study.kafka;

import cn.liangqinghai.study.kafka.utils.LoadConfigUtil;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title Streams
 * @ProjectName study-code
 * @Description
 * @date 2020/3/14 11:55
 */
public class Streams {

    private static final String TOPIC = "lqh_test";

    public static void main(String[] args) {

        Properties config = LoadConfigUtil.getKafkaConfigForStream();

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.<String, String>stream(TOPIC)
                .mapValues(value -> {
                    System.out.println(value);
                    return String.valueOf(value.length());
                }).to("lqh_test_embeded_json");

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), config);

        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

    }

}
