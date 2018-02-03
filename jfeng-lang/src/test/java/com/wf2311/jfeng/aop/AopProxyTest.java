package com.wf2311.jfeng.aop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author wangfeng
 * @time 2017/11/03 14:02.
 */
public class AopProxyTest {
    @Test
    public void proxyOf() throws Exception {
        Person person = AopProxy.proxyOf(new LoggerAspect(new Programmer()));
        person.sleep();
        assertEquals("before com.wf2311.jfeng.aop.Programmer#sleep", LoggerAspect.log);
        person.work();
    }

}