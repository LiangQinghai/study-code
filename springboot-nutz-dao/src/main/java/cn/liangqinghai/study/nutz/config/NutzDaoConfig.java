package cn.liangqinghai.study.nutz.config;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author LiangQinghai
 * @Title NutzConfig
 * @ProjectName study-code
 * @Description
 * @date 3/31/2020 5:58 PM
 */
@Configuration
public class NutzDaoConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Dao nutzDao(DataSource dataSource) {
        return new NutDao(dataSource);
    }

}