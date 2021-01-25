package cn.liangqinghai.study.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author LiangQinghai
 * @Title JpaConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/6/10 14:19
 */
@Configuration
public class JpaConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

//    @PostConstruct
//    public void init(EntityManager entityManager) {
//
//        Filter filter = entityManager.unwrap(Session.class).enableFilter("TENANT_FILTER");
//
//        filter.setParameter("tenantCode", ContextHolder.tenantCode);
//
//    }

}
