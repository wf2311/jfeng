package com.wf2311.jfeng.aop;

import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng
 * @time 2017/11/03 14:13.
 */
public class Programmer implements Person {
    @Override
    public void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void work() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
