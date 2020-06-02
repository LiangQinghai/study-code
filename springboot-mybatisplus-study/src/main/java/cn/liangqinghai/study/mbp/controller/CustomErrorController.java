package cn.liangqinghai.study.mbp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiangQinghai
 * @Title CustomErrorController
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 15:57
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
