package cn.liangqinghai.study.mbp.config;

import cn.liangqinghai.study.mbp.properties.MbpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Liang
 * @date 2020/5/19
 */
@Configuration
@EnableConfigurationProperties({MbpProperties.class})
public class MbpConfig {
}
