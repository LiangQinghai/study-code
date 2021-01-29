package cn.liangqinghai.study.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webservice</p>
 * <p>File name: WebApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/25 10:01
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebApp {

//    public static void main(String[] args) {
//        Endpoint.publish("http://172.26.57.109:8785/webservice" , new DemoImpl());
//    }

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
        System.out.println("====启动Cxf Server====");

    }
}
