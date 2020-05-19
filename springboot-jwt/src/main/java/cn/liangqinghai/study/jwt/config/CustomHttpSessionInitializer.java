package cn.liangqinghai.study.jwt.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author LiangQinghai
 * @Title CustomHttpSessionInitializer
 * @ProjectName study-code
 * @Description
 * @date 2020/5/19 11:41
 */
public class CustomHttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public CustomHttpSessionInitializer() {
        super(CustomHttpSessionConfig.class);
    }

}
