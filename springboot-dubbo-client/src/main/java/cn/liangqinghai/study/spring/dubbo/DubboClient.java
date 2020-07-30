package cn.liangqinghai.study.spring.dubbo;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.spring.dubbo.beans.MethodUriMappingBean;
import cn.liangqinghai.study.spring.dubbo.service.IService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@EnableDubbo
public class DubboClient {

    public static void main(String[] args) {

        SpringApplication.run(DubboClient.class, args);

    }

    @Autowired
    private ApplicationContext applicationContext;

    @DubboReference(version = "1.0.0")
    private IService service;

    @EventListener(ApplicationReadyEvent.class)
    public void listener() {

        Map<String, HandlerMapping> handlerMethodMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, HandlerMapping.class, true, false);

        List<MethodUriMappingBean> beans = new ArrayList<>();

        handlerMethodMap.forEach((k, v) -> {

            System.out.println(k);

            if (v instanceof RequestMappingHandlerMapping) {

                RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping) v;

                mapping.getHandlerMethods().forEach((info, map) -> {

                    Set<String> patterns = info.getPatternsCondition().getPatterns();

                    MethodUriMappingBean bean = new MethodUriMappingBean();
                    bean.setUris(patterns);
                    bean.setCategoryCode("one");
                    bean.setClassName(map.getBeanType().getCanonicalName());
                    bean.setMethod(map.getMethod().getName());

                    beans.add(bean);

                });

            }

        });


        // rpc上报
        System.out.println(service.reportRequestMapping(beans));

        String json = JSONUtil.toJsonStr(beans);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);

        // http
        RestTemplate restTemplate = new RestTemplate();
        Boolean body = restTemplate.postForEntity("http://127.0.0.1:8989/report", formEntity, Boolean.class).getBody();

        System.out.println(body);

    }

}
