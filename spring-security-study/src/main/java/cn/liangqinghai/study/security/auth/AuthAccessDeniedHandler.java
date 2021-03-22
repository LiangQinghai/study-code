package cn.liangqinghai.study.security.auth;

import cn.liangqinghai.study.security.api.ResultDTO;
import cn.liangqinghai.study.security.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.auth</p>
 * <p>File name: AuthAccessDeniedHandler</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 16:10
 */
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger log = LoggerFactory.getLogger(AuthAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("禁止访问", accessDeniedException);
        HttpUtil.writeJson(response, ResultDTO.forbidden(accessDeniedException.getMessage()));

    }
}
