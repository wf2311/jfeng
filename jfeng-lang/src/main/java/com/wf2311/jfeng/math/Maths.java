package com.wf2311.jfeng.math;

/**
 * @author wangfeng
 * @time 2017/06/14 17:02.
 */
public final class Maths {

    public static int a(int m, int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("参数必须为正数");
        }
        if (m < n) {
            throw new IllegalArgumentException("m不能大于n");
        }
        int result = m;
        while (n > 1) {
            result = result * (--m);
            n--;
        }
        return result;
    }

    public static int c(int m, int n) {
        int a = a(m, n);
        while (n > 1) {
            a = a / (n--);
        }
        return a;
    }

}
