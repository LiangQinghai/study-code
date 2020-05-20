package cn.liangqinghai.study.mbp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author LiangQinghai
 * @Title ShiroProperties
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 17:55
 */
@Data
@ConfigurationProperties(prefix = ShiroProperties.SHIRO)
public class ShiroProperties {

    public static final String SHIRO = "shiro";

    private String loginUrl;

    private String unauthUrl;

    private String indexUrl;

    @NestedConfigurationProperty
    private RememberMeCookie rememberMeCookie;

    @NestedConfigurationProperty
    private Session session;

}
