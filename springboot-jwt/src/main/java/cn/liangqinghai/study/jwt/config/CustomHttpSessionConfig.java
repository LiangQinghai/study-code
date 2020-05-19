package cn.liangqinghai.study.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author LiangQinghai
 * @Title CustomHttpSessionConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/19 11:39
 */
@EnableRedisHttpSession
public class CustomHttpSessionConfig {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

}
