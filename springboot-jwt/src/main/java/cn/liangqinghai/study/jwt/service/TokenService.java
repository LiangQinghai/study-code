package cn.liangqinghai.study.jwt.service;

import cn.liangqinghai.study.jwt.module.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Liang
 * @date 2020/4/19
 */
@Service
public class TokenService {

    public String getToken(User user) {

        return JWT.create()
                .withAudience(user.getId() + "") // 保存到token的信息
                .sign(Algorithm.HMAC256(user.getPassword()));// secret

    }

}
