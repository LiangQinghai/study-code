package cn.liangqinghai.study.idempotent.config;

import cn.liangqinghai.study.idempotent.interceptor.IdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiangQinghai
 * @Title WenConfig
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 8:05 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private IdempotentInterceptor idempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idempotentInterceptor);
    }
}
