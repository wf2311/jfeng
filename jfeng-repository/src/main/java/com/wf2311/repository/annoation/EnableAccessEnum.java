package com.wf2311.repository.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangfeng
 * @time 2017/06/22 16:58.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableAccessEnum {

    String name() default "";

//    Class<?> type() default "";
}
