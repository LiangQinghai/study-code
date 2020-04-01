package cn.liangqinghai.study.nutz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author LiangQinghai
 * @Title Main
 * @ProjectName study-code
 * @Description
 * @date 3/31/2020 5:26 PM
 */
@SpringBootApplication
@EnableTransactionManagement
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
