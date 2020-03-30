package cn.liangqinghai.study.idempotent.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LiangQinghai
 * @Title Idempotent
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 7:45 PM
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
}
