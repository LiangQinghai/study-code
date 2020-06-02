package cn.liangqinghai.study.mbp.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiangQinghai
 * @Title SysPageController
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 15:59
 */
@Controller
public class SysPageController {

    @RequestMapping("/admin/{path}/{ur}.html")
    public String page(
            @PathVariable("path") String path,
            @PathVariable("ur") String uri) {
        return "/admin/" + path + "/" + uri;
    }

    @RequestMapping("/admin/{ur}.html")
    public String pareUrl(@PathVariable("ur") String uri) {
        return "/admin/" + uri;
    }

}
