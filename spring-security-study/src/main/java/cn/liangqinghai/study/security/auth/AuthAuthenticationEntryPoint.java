package cn.liangqinghai.study.security.auth;

import cn.liangqinghai.study.security.api.ResultDTO;
import cn.liangqinghai.study.security.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.auth</p>
 * <p>File name: AuthAuthenticationEntryPoint</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 16:19
 */
@Component
public class AuthAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(AuthAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("认证异常", authException);
        HttpUtil.writeJson(response, ResultDTO.unauthorized(authException.getMessage()));
    }
}
