package com.wf2311.jfeng.text;

/**
 * @author wf2311
 */
public class IndexRange {
    private int start;
    private int end;


    public IndexRange start(int start) {
        this.start = start;
        return this;
    }

    public IndexRange end(int end) {
        this.end = end;
        return this;
    }

    /**
     * 获取 end 的值
     *
     * @see IndexRange#end
     */
    public int end() {
        return end;
    }

    /**
     * 获取 start 的值
     *
     * @see IndexRange#start
     */
    public int start() {
        return start;
    }
}
