package cn.liangqinghai.study.idempotent.resources;

import cn.liangqinghai.study.idempotent.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangQinghai
 * @Title TokenResource
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 8:10 PM
 */
@RestController
@RequestMapping("/token")
public class TokenResource {

    @Autowired
    private ITokenService tokenService;

    @GetMapping("/get")
    public String getToken() {
        return tokenService.getToken();
    }

}
