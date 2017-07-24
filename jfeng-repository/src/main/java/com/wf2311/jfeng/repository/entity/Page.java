package com.wf2311.jfeng.repository.entity;

/**
 * @author wf2311
 */
public class Page {
    /**
     * 查询起始页码
     */
    private int pageNum;
    /**
     * 当pageSize小于0时,为查询所有
     */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
