package cn.liangqinghai.study.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.mybatis</p>
 * <p>File name: MyBatisApp</p>
 * <div>
 * <h3>Description: </h3>
 * </disv>
 *
 * @author LiangQinghai
 * @since 2021/1/23 10:57
 */
@SpringBootApplication
@MapperScan("cn.liangqinghai.study.mybatis.mapper")
public class MyBatisApp {

    public static void main(String[] args) {

        SpringApplication.run(MyBatisApp.class, args);

    }

}
