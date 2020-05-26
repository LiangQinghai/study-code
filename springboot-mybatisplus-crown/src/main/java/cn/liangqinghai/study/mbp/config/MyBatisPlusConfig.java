package cn.liangqinghai.study.mbp.config;

import cn.liangqinghai.study.mbp.common.mbp.CommonMetaObjectHandler;
import cn.liangqinghai.study.mbp.common.mbp.MbpSqlInjector;
import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author LiangQinghai
 * @Title MyBatisPlusConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/26 19:52
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 读写分离
     *
     * @return
     */
    @Bean
    public MasterSlaveAutoRoutingPlugin masterSlaveAutoRoutingPlugin() {
        return new MasterSlaveAutoRoutingPlugin();
    }

    /**
     * 乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public CommonMetaObjectHandler commonMetaObjectHandler() {
        return new CommonMetaObjectHandler();
    }

    @Bean
    public MbpSqlInjector mbpSqlInjector() {
        return new MbpSqlInjector();
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {

        PageInterceptor pageInterceptor = new PageInterceptor();

        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");

        pageInterceptor.setProperties(properties);
        return pageInterceptor;

    }

}
