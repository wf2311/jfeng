package com.wf2311.jfeng.lang.random;

/**
 * @author wangfeng
 * @time 2017/04/07 18:54.
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
        this.rate = rate;
        this.start = start;
        this.end = end;
    }

    public Condition(double rate, int start, int end) {
        this.rate = rate;
        this.start = start;
        this.end = end;
    }

    public Condition(double rate, double value) {
        this.rate = rate;
        this.start = value;
        this.end = value;
    }

    public Condition(double rate, int value) {
        this.rate = rate;
        this.start = value;
        this.end = value;
    }

    public Condition(double rate, String value) {
        this.rate = rate;
        this.value = value;
    }
}
