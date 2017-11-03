package com.wf2311.jfeng.aop;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

public class LoggerAspect extends Aspect {

    private LocalDateTime start;
    private LocalDateTime end;

    public static String log = "";

    public LoggerAspect(Object target) {
        super(target);
    }

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        log += "before ";
        start = LocalDateTime.now();
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        end = LocalDateTime.now();
        log += target.getClass().getName() + '#' + method.getName();
        System.out.println("speed:" + Duration.between(start, end).toMillis());
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable throwable) {
        return true;
    }
}
