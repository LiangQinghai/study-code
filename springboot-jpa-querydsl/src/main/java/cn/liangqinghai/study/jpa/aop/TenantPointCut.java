package cn.liangqinghai.study.jpa.aop;

import cn.liangqinghai.study.jpa.annos.EnableTenant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TenantPointCut {

    private static final Logger log = LoggerFactory.getLogger(TenantPointCut.class);

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
