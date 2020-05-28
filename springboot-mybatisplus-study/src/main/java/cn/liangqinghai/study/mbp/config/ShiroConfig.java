package cn.liangqinghai.study.mbp.config;

import cn.liangqinghai.study.mbp.shiro.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.LinkedHashMap;

/**
 * @author LiangQinghai
 * @Title ShiroConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 14:24
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * realm
     *
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * 缓存管理
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {

        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;

    }

    @Bean
    public SimpleCookie simpleCookie() {

        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);

        return cookie;

    }

    /**
     * cookie管理
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {

        CookieRememberMeManager manager = new CookieRememberMeManager();

        manager.setCookie(simpleCookie());

        byte[] decode = Base64.getDecoder().decode("wGiHplamyXlVB11UXWol8g==");
        manager.setCipherKey(decode);

        return manager;

    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setRealm(userRealm);
        manager.setCacheManager(ehCacheManager());
        manager.setRememberMeManager(cookieRememberMeManager());

        return manager;

    }

    /**
     * shiro aop
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;

    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        factoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> chainMap = new LinkedHashMap<>();

        chainMap.put("/admin/sys/logout", "logout");
        chainMap.put("/admin/index", "user");

        // authc: 认证访问
        // anon: 无需认证
        chainMap.put("/static/**", "anon");
        chainMap.put("/admin/captcha.jpg", "anon");
        chainMap.put("/admin/sys/login", "anon");
        chainMap.put("/admin/**", "authc");

        factoryBean.setLoginUrl("/admin/login.html");
        factoryBean.setSuccessUrl("/admin/");
        factoryBean.setUnauthorizedUrl("/error.html");

        factoryBean.setFilterChainDefinitionMap(chainMap);

        return factoryBean;

    }

}
