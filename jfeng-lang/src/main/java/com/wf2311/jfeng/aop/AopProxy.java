package com.wf2311.jfeng.aop;

import com.wf2311.jfeng.reflect.ReflectUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wangfeng
 * @time 2017/11/03 13:48.
 */
public class AopProxy implements Serializable{

    /**
     * 根据给定的对象和切面创建一个对象代理
     *
     * @param target      要代理的对象
     * @param aspectClass 切连类型
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxyOf(T target, Class<? extends Aspect> aspectClass) {
        final Aspect aspect;
        try {
            aspect = ReflectUtils.newInstance(aspectClass, target);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't create new instance of aspect class", e);
        }
        return (T) newProxyInstance(target.getClass().getClassLoader(), aspect, target.getClass().getInterfaces());
    }

    /**
     * Creates a proxy from given {@link Aspect}.
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxyOf(Aspect aspect) {
        final Object target = aspect.getTarget();
        return (T) newProxyInstance(target.getClass().getClassLoader(), aspect, target.getClass().getInterfaces());
    }

    /**
     * Simple wrapper for javas {@code newProxyInstance}.
     */
    @SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(ClassLoader classloader, InvocationHandler invocationHandler, Class<?>... interfaces) {
        if (interfaces.length == 0) {
            throw new IllegalArgumentException("No interfaces of target class found.");
        }
        return (T) Proxy.newProxyInstance(classloader, interfaces, invocationHandler);
    }
}
