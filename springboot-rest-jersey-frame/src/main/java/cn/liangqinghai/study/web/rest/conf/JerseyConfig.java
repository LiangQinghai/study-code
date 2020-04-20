package cn.liangqinghai.study.web.rest.conf;

import cn.liangqinghai.study.web.rest.handlers.GlobalErrorHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LiangQinghai
 * @Title JerseyConfig
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 4:12 PM
 */
@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GlobalErrorHandler.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));

        LinkedHashSet<BeanDefinition> beanDefinitions = new LinkedHashSet<>(scanner.findCandidateComponents("cn.liangqinghai.study.rest"));

        this.register(beanDefinitions.stream()
                .map(beanDefinition -> ClassUtils.resolveClassName(Objects.requireNonNull(beanDefinition.getBeanClassName()), this.getClassLoader()))
                .collect(Collectors.toSet()));

    }

}
