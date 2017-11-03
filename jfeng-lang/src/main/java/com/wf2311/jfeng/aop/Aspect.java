package com.wf2311.jfeng.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wf2311
 * @time 2017/11/03 13:30.
 */
public abstract class Aspect implements InvocationHandler {


    private Object target;

    public Aspect(Object target) {
        this.target = target;
    }

    public final Object getTarget() {
        return this.target;
    }

    /**
     * 在目标方法执行前运行
     *
     * @param target 目标对象
     * @param method 要执行的方法
     * @param args   参数
     * @return 是否要执行invoke方法
     */
    public abstract boolean before(Object target, Method method, Object[] args);

    /**
     * 在目标方法执行完成后运行
     *
     * @param target 目标对象
     * @param method 要执行的方法
     * @param args   参数
     * @return
     */
    public abstract boolean after(Object target, Method method, Object[] args);

    /**
     * 在目标方法执行发生异常后执行
     *
     * @param target    目标对象
     * @param method    要执行的方法
     * @param args      参数
     * @param throwable 异常类型
     * @return 执行的invoke方法是否要返回值
     */
    public abstract boolean afterException(Object target, Method method, Object[] args, Throwable throwable);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        if (before(target, method, args)) {
            try {
                result = method.invoke(target, args);
            } catch (InvocationTargetException e) {
                afterException(args, method, args, e.getTargetException());
            } catch (Exception ex) {
                throw ex;
            }
        }
        if (after(target, method, args)) {
            return result;
        }
        return null;
    }
}
