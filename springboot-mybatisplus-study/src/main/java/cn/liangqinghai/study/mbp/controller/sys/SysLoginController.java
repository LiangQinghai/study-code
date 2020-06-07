package cn.liangqinghai.study.mbp.controller.sys;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.service.sys.SysUserService;
import cn.liangqinghai.study.mbp.utils.HttpUtil;
import cn.liangqinghai.study.mbp.utils.Result;
import cn.liangqinghai.study.mbp.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/")
    public String redirect() {
        if (ShiroUtils.isLogin()) {
            return "redirect:/admin/index.html";
        } else {
            return "/admin/login";
        }
    }

    @PostMapping("/sys/login")
    @ResponseBody
    public Result login(String username,
                        String password,
                        HttpServletRequest request) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {

            return Result.error("用户名或密码错误");

        }

        try {
            Subject subject = ShiroUtils.getSubject();
            String hex = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, hex);
            subject.login(token);
        } catch (AuthenticationException e) {
            return Result.error(e.getMessage());
        }

        SysUser currentUser = getAdmin();
        String ipAddress = HttpUtil.getIpAddress(request);

        currentUser.setLastLoginIp(ipAddress)
                .setLastLoginTime(System.currentTimeMillis());


        sysUserService.updateById(currentUser);

        return Result.ok();

    }

}
