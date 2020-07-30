package cn.liangqinghai.study.spring.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LiangQinghai
 * @title DubboServer
 * @projectName study-code
 * @description
 * @date 2020/7/30 19:48
 */
@SpringBootApplication
@EnableDubbo
public class DubboServer {

    public static void main(String[] args) {

        SpringApplication.run(DubboServer.class, args);

    }

}
