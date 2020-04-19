package cn.liangqinghai.study.jwt.apis;

import cn.hutool.json.JSONObject;
import cn.liangqinghai.study.jwt.annos.UserLoginToken;
import cn.liangqinghai.study.jwt.module.User;
import cn.liangqinghai.study.jwt.service.TokenService;
import cn.liangqinghai.study.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mr.Liang
 * @date 2020/4/19
 */
@RestController
public class UserApi {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody User user) {

        User login = userService.login(user);

        if (login == null) {
            throw new RuntimeException("Not exit");
        }

        JSONObject jsonObject = new JSONObject();

        String token = tokenService.getToken(user);
        jsonObject.put("token",token);
        jsonObject.put("user", login);

        return jsonObject;

    }

    @UserLoginToken(required = true)
    @GetMapping("/users")
    public List<User> users() {

        return userService.getAll();

    }

}
