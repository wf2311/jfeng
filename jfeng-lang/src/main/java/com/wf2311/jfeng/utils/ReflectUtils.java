package com.wf2311.jfeng.utils;


import java.lang.reflect.Field;

/**
 * @author wf2311
 * @time 2016/10/15 21:48.
 */
public class ReflectUtils {

    /**
     * @param object 对象实例
     * @param field  成员变量
     * @param value  要赋予的值
     * @throws IllegalAccessException
     */
    public static void setFiledValue(Object object, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @param value     要赋予的值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setFiledValue(Object object, String filedName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getFiled(object, filedName);
        setFiledValue(object, field, value);
    }

    /**
     * @param object 对象实例
     * @param field  成员变量
     * @return 成员变量值
     * @throws IllegalAccessException
     */
    public static Object getFiledValue(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @return 成员变量值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFiledValue(Object object, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field filed = getFiled(object, filedName);
        return getFiledValue(object, filed);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @return 成员变量域
     * @throws NoSuchFieldException
     */
    public static Field getFiled(Object object, String filedName) throws NoSuchFieldException {
        return object.getClass().getDeclaredField(filedName);
    }

}
