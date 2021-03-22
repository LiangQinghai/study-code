package cn.liangqinghai.study.druid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.druid</p>
 * <p>File name: HelloController</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/2/2 9:39
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping("/test")
    public String hello(HttpServletRequest request) {

        System.out.println(request.getRequestURI());

        return "hello";

    }

}
