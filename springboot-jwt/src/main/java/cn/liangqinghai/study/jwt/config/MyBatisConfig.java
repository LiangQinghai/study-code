package cn.liangqinghai.study.jwt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiangQinghai
 * @Title MyBatisConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/19 19:34
 */
@Configuration
@MapperScan("cn.liangqinghai.study.jwt.mapper")
public class MyBatisConfig {
}
