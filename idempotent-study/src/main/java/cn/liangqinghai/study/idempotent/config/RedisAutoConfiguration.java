package cn.liangqinghai.study.idempotent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author LiangQinghai
 * @Title RedisAutoConfiguration
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 8:33 PM
 */
@Configuration
public class RedisAutoConfiguration {

    @Value("${spring.redis.host}")
    private String ip;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public JedisPool jedisPool() {

        return new JedisPool(ip, port);

    }

}
