package cn.liangqinghai.study.web.rest.conf;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author LiangQinghai
 * @Title BeanMapperConfig
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 3:32 PM
 */
@Configuration
public class BeanMapperConfig {

    @Bean
    public Mapper mapper() {

        return new DozerBeanMapper(Collections.singletonList("classpath:dozer-global-mappings.xml"));

    }

}
