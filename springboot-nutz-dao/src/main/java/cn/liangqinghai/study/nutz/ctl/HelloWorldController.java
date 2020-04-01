package cn.liangqinghai.study.nutz.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangQinghai
 * @Title HelloWorldContoller
 * @ProjectName study-code
 * @Description
 * @date 4/1/2020 2:45 PM
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

}
