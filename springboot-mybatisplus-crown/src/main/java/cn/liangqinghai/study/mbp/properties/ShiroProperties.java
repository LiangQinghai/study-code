package cn.liangqinghai.study.mbp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author LiangQinghai
 * @Title ShiroProperties
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 17:55
 */
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

    public static String getSHIRO() {
        return SHIRO;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public ShiroProperties setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        return this;
    }

    public String getUnauthUrl() {
        return unauthUrl;
    }

    public ShiroProperties setUnauthUrl(String unauthUrl) {
        this.unauthUrl = unauthUrl;
        return this;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public ShiroProperties setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
        return this;
    }

    public RememberMeCookie getRememberMeCookie() {
        return rememberMeCookie;
    }

    public ShiroProperties setRememberMeCookie(RememberMeCookie rememberMeCookie) {
        this.rememberMeCookie = rememberMeCookie;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public ShiroProperties setSession(Session session) {
        this.session = session;
        return this;
    }
}
