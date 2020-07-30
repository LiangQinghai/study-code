package cn.liangqinghai.study.spring.dubbo.controller;

import cn.liangqinghai.study.spring.dubbo.beans.MethodUriMappingBean;
import cn.liangqinghai.study.spring.dubbo.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {


    @Autowired
    private IService service;

    @PostMapping("/report")
    public boolean report(@RequestBody List<MethodUriMappingBean> beans) {

        beans.forEach(System.out::println);

        return true;

    }


}
