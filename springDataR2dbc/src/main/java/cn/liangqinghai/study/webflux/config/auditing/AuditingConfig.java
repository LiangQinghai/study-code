package cn.liangqinghai.study.webflux.config.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webflux.config.auditing</p>
 * <p>File name: AuditingConfig</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/30 11:38
 */
@Configuration
@EnableR2dbcAuditing
public class AuditingConfig {

    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return new CurrentUserAware();
    }

}
