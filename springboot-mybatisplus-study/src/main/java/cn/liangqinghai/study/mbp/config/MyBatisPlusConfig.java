package cn.liangqinghai.study.mbp.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiangQinghai
 * @Title MyBatisPlusConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 14:15
 */
@Configuration
@MapperScan("cn.liangqinghai.study.mbp.dao*")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDbType(DbType.MYSQL);

        return interceptor;

    }

}
