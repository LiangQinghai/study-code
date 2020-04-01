package cn.liangqinghai.study.web.rest.conf;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author LiangQinghai
 * @Title MyBatisConfig
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 5:00 PM
 */
@Configuration
@MapperScan({"cn.liangqinghai.study.web.rest.mapper"})
public class MyBatisConfig {
}
