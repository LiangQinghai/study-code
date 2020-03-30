package cn.liangqinghai.study.web.rest.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.interceptor.*;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author LiangQinghai
 * @Title TransationConfig
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 5:08 PM
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureBefore(ProxyTransactionManagementConfiguration.class)
public class TransactionConfig extends ProxyTransactionManagementConfiguration {

    @Override
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public TransactionAttributeSource transactionAttributeSource() {
        return new CompositeTransactionAttributeSource(new AnnotationTransactionAttributeSource(),
                new CustomNameMatchTransactionAttributeSource());
    }

    /**
     * 激活注解{@link EnableTransactionManagement}
     */
    @Configuration
    @ConditionalOnBean(PlatformTransactionManager.class)
    public static class EnableTransactionManagementConfiguration {

        @Configuration
        @EnableTransactionManagement
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false")
        public static class JdkDynamicAutoProxyConfiguration {

        }

        @Configuration
        @EnableTransactionManagement(proxyTargetClass = true)
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true", matchIfMissing = true)
        public static class CglibAutoProxyConfiguration {

        }
    }

    /**
     *
     */
    @Slf4j
    public static class CustomNameMatchTransactionAttributeSource extends NameMatchTransactionAttributeSource {

        private static final long serialVersionUID = 3126090067591402444L;

        public CustomNameMatchTransactionAttributeSource() {
            RuleBasedTransactionAttribute readTx = new RuleBasedTransactionAttribute();
            readTx.setReadOnly(true);
            readTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

            RuleBasedTransactionAttribute requireTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
            setNameMap(new HashMap<String, TransactionAttribute>() {

                private static final long serialVersionUID = 5726720265438891670L;

                {
                    put("get*", readTx);
                    put("find*", readTx);
                    put("list*", readTx);
                    put("count*", readTx);
                    put("insert*", requireTx);
                    put("save*", requireTx);
                    put("update*", requireTx);
                    put("delete*", requireTx);
                }

            });

        }

        @Override
        public TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass) {
            if (targetClass.isAnnotationPresent(Service.class)) {
                return super.getTransactionAttribute(method, targetClass);
            }
            return null;
        }
    }

}
