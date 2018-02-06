package com.wf2311.jfeng.random;

/**
 * 随机数分布条件
 *
 * @author wangfeng
 */
public class Condition {
    /**
     * 概率
     */
    protected double rate;

    /**
     * 开始值
     */
    protected double start;

    /**
     * 结束值
     */
    protected double end;

    protected String value;

    public Condition(double rate, double start, double end) {
        if (rate < 0 || start > end) {
            throw new IllegalArgumentException();
        }
        this.rate = rate;
        this.start = start;
        this.end = end;
    }

    public Condition(double rate, int start, int end) {
        this(rate, (double) start, (double) end);
    }

    public Condition(double rate, double value) {
        this(rate, value, value);
    }

    public Condition(double rate, int value) {
        this(rate, (double) value, (double) value);
    }

    public Condition(double rate, String value) {
        if (rate < 0) {
            throw new IllegalArgumentException();
        }
        this.rate = rate;
        this.value = value;
    }

    public static Condition with(double rate, double start, double end) {
        return new Condition(rate, start, end);
    }

    public static Condition with(double rate, double value) {
        return new Condition(rate, value, value);
    }

    public static Condition with(double rate, int value) {
        return new Condition(rate, (double) value, (double) value);
    }


    public static Condition with(double rate, String value) {
        return new Condition(rate, value);
    }


}
