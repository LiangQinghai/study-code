package cn.liangqinghai.study.webflux.config.auditing;

import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.core.publisher.Mono;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webflux.config.auditing</p>
 * <p>File name: CurrentUserAware</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/30 11:36
 */
public class CurrentUserAware implements ReactiveAuditorAware<String> {
    @Override
    public Mono<String> getCurrentAuditor() {
        return Mono.just("hello");
    }
}
