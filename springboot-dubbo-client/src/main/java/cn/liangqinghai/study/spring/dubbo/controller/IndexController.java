package cn.liangqinghai.study.spring.dubbo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping({"/", "/one"})
    public String one() {
        return "one";
    }

    @PostMapping("/two")
    public void two() {

        System.out.println("two");

    }

}
