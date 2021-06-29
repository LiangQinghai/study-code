package cn.liangqinghai.study.dynamic.datasource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.liangqinghai.study.dynamic.datasource.*.*.mapper.*")
public class MyBatisConfig {

}
