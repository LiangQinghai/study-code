package cn.liangqinghai.study.jpa.annos;

import java.lang.annotation.*;

/**
 * @author LiangQinghai
 * @Title EnableTenant
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 14:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableTenant {
}
