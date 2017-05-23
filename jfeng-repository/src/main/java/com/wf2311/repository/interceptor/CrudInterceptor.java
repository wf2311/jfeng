package com.wf2311.repository.interceptor;

import com.wf2311.jfeng.id.IdCreator;
import com.wf2311.jfeng.utils.ReflectUtils;
import com.wf2311.repository.annoation.*;
import com.wf2311.repository.consts.Constants;
import com.wf2311.repository.entity.BaseUser;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author wf2311
 * @time 2016/06/12 14:03.
 */
public abstract class CrudInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CrudInterceptor.class);

    protected static List<? extends Object> EXCLUDE_MODEL= Arrays.asList(
    );

    protected final static IdCreator idCreator = new IdCreator(1, 1);

    public boolean shouldIntercept(Object arg){
        return !EXCLUDE_MODEL.contains(arg.getClass());
    }

    public BaseUser getUserInfo() {

        BaseUser userInfo = new BaseUser();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    
            userInfo = (BaseUser) request.getAttribute(Constants.USER_INFO);
            if (userInfo == null || userInfo.getId() == null) {
                userInfo = new BaseUser();
                userInfo.setId(Constants.ADMIN_ID);
            }
        } catch (Exception e) {
            userInfo.setId(Constants.ADMIN_ID);
        }
        return userInfo;
    }

    public abstract void before(JoinPoint joinPoint);

    public boolean existMethod(Object o,String name, Class<?> c) {
        try {
            Method method = o.getClass().getMethod(name, c);
            return method != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public boolean existMethod(Object o,String name) {
        try {
            Method method = o.getClass().getMethod(name);
            return method != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }


    protected static boolean shouldSetPrimary(Object obj, Field field) {
        if (field.isAnnotationPresent(Primary.class)
                && !field.isAnnotationPresent(AutoIncrement.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    protected static boolean shouldSetCreator(Object obj, Field field) {
        if (field.isAnnotationPresent(Creator.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    protected static boolean shouldSetCreatedTime(Object obj, Field field) {
        if (field.isAnnotationPresent(CreatedTime.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    static boolean shouldSetModifier(Object obj, Field field) {
        if (field.isAnnotationPresent(Modifier.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    protected static boolean shouldSetModifiedTime(Object obj, Field field) {
        if (field.isAnnotationPresent(ModifiedTime.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }
}
