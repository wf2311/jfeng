package com.wf2311.jfeng.repository.aspect.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wf2311
 */
public interface Condition {

    default boolean existMethod(Object o, String name, Class<?> c) {
        try {
            Method method = o.getClass().getMethod(name, c);
            return method != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    default boolean existMethod(Object o, String name) {
        try {
            Method method = o.getClass().getMethod(name);
            return method != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 是否要设置主键值
     */
    boolean shouldSetPrimary(Object obj, Field field);

    /**
     * 是否要设置创建者
     */
    boolean shouldSetCreator(Object obj, Field field);

    /**
     * 是否要设置创建时间
     */
    boolean shouldSetCreatedTime(Object obj, Field field);

    /**
     * 是否要设置修改者
     */
    boolean shouldSetModifier(Object obj, Field field);

    /**
     * 是否要设置修改时间
     */
    boolean shouldSetModifiedTime(Object obj, Field field);
}
