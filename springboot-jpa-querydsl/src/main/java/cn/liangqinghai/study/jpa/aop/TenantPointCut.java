package cn.liangqinghai.study.jpa.aop;

import cn.liangqinghai.study.jpa.annos.EnableTenant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author LiangQinghai
 * @Title TenatPointCut
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 14:28
 */
@Aspect
@Component
@Slf4j
public class TenantPointCut {

    @Pointcut(value = "@annotation(enableTenant)")
    public void pointcut(EnableTenant enableTenant) {}

//    @Around(value = "pointcut(enableTenant)", argNames = "joinPoint, enableTenant")
//    public Object around(ProceedingJoinPoint joinPoint, EnableTenant enableTenant) {
//
//        try {
//            System.out.println("----");
//            return joinPoint.proceed();
//        } catch (Throwable throwable) {
//            log.error(throwable.getMessage(), throwable);
//            throw new RuntimeException(throwable);
//        }
//
//    }

    @Before(value = "pointcut(enableTenant)", argNames = "enableTenant")
    public void before(EnableTenant enableTenant) {

        log.info("开启租户隔离条件.");

    }

}
