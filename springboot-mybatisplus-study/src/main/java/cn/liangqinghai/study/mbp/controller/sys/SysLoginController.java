package cn.liangqinghai.study.mbp.controller.sys;

import cn.liangqinghai.study.mbp.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiangQinghai
 * @Title SysLoginController
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 16:57
 */
@Controller
@RequestMapping("/admin")
public class SysLoginController extends AbstractController{

    @RequestMapping("/")
    public String redirect() {
        if (ShiroUtils.isLogin()) {
            return "redirect:/admin/index.html";
        } else {
            return "/admin/login";
        }
    }

}
