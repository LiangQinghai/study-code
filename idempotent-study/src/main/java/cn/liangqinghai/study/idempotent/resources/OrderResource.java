package cn.liangqinghai.study.idempotent.resources;

import cn.liangqinghai.study.idempotent.annotations.Idempotent;
import cn.liangqinghai.study.idempotent.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title OrderResource
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 8:12 PM
 */
@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private ITokenService tokenService;

    @PostMapping("/create")
    @ResponseBody
    @Idempotent
    public String order(Integer id, String name, Long money, HttpServletRequest request) {

        String uuid = request.getHeader("uuid");

        if (StringUtils.isEmpty(uuid)) {
            return "Error";
        }

        tokenService.removeToken(uuid);

        // 模拟一堆业务处理
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id + name + money;

    }

}
