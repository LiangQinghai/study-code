package cn.liangqinghai.study.idempotent.interceptor;

import cn.liangqinghai.study.idempotent.annotations.Idempotent;
import cn.liangqinghai.study.idempotent.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiangQinghai
 * @Title IdempotentInterceptor
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 7:56 PM
 */
@Component
public class IdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;

        Idempotent idempotent = method.getMethod().getAnnotation(Idempotent.class);

        if (idempotent != null) {
            boolean checkToken = tokenService.checkToken(request);

            if (!checkToken) {
                throw new RuntimeException("hhhhhh");
            }

            return checkToken;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
