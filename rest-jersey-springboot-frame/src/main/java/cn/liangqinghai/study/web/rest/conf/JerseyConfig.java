package cn.liangqinghai.study.web.rest.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

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
}
