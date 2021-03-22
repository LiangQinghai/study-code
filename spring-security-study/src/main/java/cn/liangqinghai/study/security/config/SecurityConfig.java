package cn.liangqinghai.study.security.config;

import cn.liangqinghai.study.security.auth.AuthAccessDeniedHandler;
import cn.liangqinghai.study.security.auth.AuthAuthenticationEntryPoint;
import cn.liangqinghai.study.security.auth.JwtAuthenticationFilter;
import cn.liangqinghai.study.security.repository.UserRepository;
import cn.liangqinghai.study.security.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.config</p>
 * <p>File name: SecurityConfig</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 14:30
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserRepository userRepository;
    @Resource
    private AuthAccessDeniedHandler authAccessDeniedHandler;
    @Resource
    private AuthAuthenticationEntryPoint authAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // jwt不使用csrf
        http.csrf()
                .disable()
                // token忽略session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "*.html",
                        "*.ico",
                        "*.css",
                        "*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                ).permitAll()
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();

        // 设置jwt拦截器
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                // 403处理器
                .accessDeniedHandler(authAccessDeniedHandler)
                // 认证异常处理器
                .authenticationEntryPoint(authAuthenticationEntryPoint);

    }

    @Bean
    public Filter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl(userRepository);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
