package cn.liangqinghai.study.mbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Mr.Liang
 * @date 2020/5/19
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MbPApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbPApplication.class, args);
    }

}
