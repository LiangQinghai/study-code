package cn.liangqinghai.study.sqltoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.sqltoy</p>
 * <p>File name: SqltoyApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/28 14:20
 */
@SpringBootApplication(scanBasePackages = {"cn.liangqinghai.study.sqltoy", "com.sqltoy.config"})
@EnableTransactionManagement
public class SqltoyApp {

    public static void main(String[] args) {

        SpringApplication.run(SqltoyApp.class, args);

    }

}
