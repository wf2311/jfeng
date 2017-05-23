package com.wf2311.repository.interceptor;

import com.wf2311.jfeng.utils.ReflectUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author wf2311
 * @time 2016/06/12 14:00.
 */
@Aspect
public class UpdateInterceptor extends CrudInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UpdateInterceptor.class);

    @Pointcut("execution(* com.wf2311.*.*.mapper.*Mapper.update*(..))")
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Field[] fields = arg.getClass().getDeclaredFields();
            if (ArrayUtils.isNotEmpty(fields)) {
                try {
                    for (Field field : fields) {
                        Annotation[] as = fields.getClass().getDeclaredAnnotations();
                        if (ArrayUtils.isNotEmpty(as)) {
                            if (shouldSetModifier(arg, field)) {
                                ReflectUtils.setFiledValue(arg, field, getUserInfo().getId());
                                continue;
                            }
                            if (shouldSetModifiedTime(arg, field)) {
                                ReflectUtils.setFiledValue(arg, field, LocalDateTime.now());
                                continue;
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
