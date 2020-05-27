package cn.liangqinghai.study.mbp.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author LiangQinghai
 * @Title EhCacheConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 16:44
 */
@Configuration
public class EhCacheConfig {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {

        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());

    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {

        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();

        factoryBean.setConfigLocation(new ClassPathResource("ehcache-core.xml"));
        factoryBean.setShared(true);

        return factoryBean;

    }

}
