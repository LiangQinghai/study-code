package cn.liangqinghai.study.idempotent.config;

import com.fasterxml.classmate.TypeResolver;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title SwaggerConfig
 * @ProjectName study-code
 * @Description
 * @date 4/8/2020 9:50 AM
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnProperty(value = {"knife4j.enable"}, matchIfMissing = true)
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("2.0.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.liangqinghai.study"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();

        List<Parameter> parameters = Lists.newArrayList();

        parameterBuilder.name("token")
                .description("token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(true)
                .build();

        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("default")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.liangqinghai.study"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return null;
    }

}
