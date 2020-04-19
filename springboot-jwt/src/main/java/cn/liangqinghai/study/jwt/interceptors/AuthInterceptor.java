package cn.liangqinghai.study.jwt.interceptors;

import cn.liangqinghai.study.jwt.annos.UserLoginToken;
import cn.liangqinghai.study.jwt.module.User;
import cn.liangqinghai.study.jwt.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Mr.Liang
 * @date 2020/4/19
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(UserLoginToken.class)) {

            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()) {

                String token = request.getHeader("token");

                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("token is null.");
                }

                String userId = null;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    e.printStackTrace();
                }

                User user = userService.getById(userId);
                if (user == null) {
                    throw new RuntimeException("userNotExit");
                }

                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token invalid");
                }

                return true;
            }

        }

        return true;
    }
}
