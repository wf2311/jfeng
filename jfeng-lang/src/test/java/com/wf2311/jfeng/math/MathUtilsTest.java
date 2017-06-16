package com.wf2311.jfeng.math;

import org.junit.Test;


/**
 * @author wangfeng
 * @time 2017/06/14 17:19.
 */
public class MathUtilsTest {
    @Test
    public void a() throws Exception {
        int s = 2;
        for (int i = 1; i <= s; i++) {
            System.out.println(Maths.a(s, i));
        }
    }

    @Test
    public void c() throws Exception {
        int s = 10;
        for (int i = 1; i <= s; i++) {
            System.out.println(Maths.c(s, i));
        }
    }

}