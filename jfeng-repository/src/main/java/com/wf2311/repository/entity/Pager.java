package com.wf2311.repository.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 基本分页组件
 *
 * @author wf2311
 * @date 2016/03/10 18:46:07
 */
public class Pager<T> implements Serializable {

    /**
     * 默认每页数据集大小
     */
    private static final int PAGE_SIZE = 10;

    /**
     * 查询起始页码
     */
    private int pageNum = 1;
    /**
     * 当pageSize小于0时,为查询所有
     */
    private int pageSize = PAGE_SIZE;
    /**
     * 符合查询条件数据总数
     */
    private long total;
    /**
     * 当前页第一个数据在totalCount中的位置
     */
    private int startIndex;
    /**
     * 当前查询页中的数据
     */
    private List<T> data = Collections.EMPTY_LIST;

    /**
     * 是否统计总数(默认开启)
     */
    private boolean count = true;

    /**
     * 当前页码大于总页码时，是否修改其值为总页码
     */
    private boolean repair = true;

    /**
     * 总页码
     */
    private int pages;

    /**
     * 生成分页实体，每页数据集大小为10
     *
     * @param pageNum 当前页码
     */
    public Pager(int pageNum) {
        this(pageNum, PAGE_SIZE);
    }

    /**
     * 生成分页实体
     *
     * @param pageNum  当前页面
     * @param pageSize 每页数据集大小
     */
    public Pager(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = new ArrayList<>();
    }

    /**
     * <p>此方法用于处理以下情况:当前页码大于总页码时，修改其值为总页码</p>
     * <strong>注意:</strong>
     * <red>此方法必须在先设置了totalCount的值后，并且查询content之前使用</red>
     */
    public void repair() {
        //当前页码大于总页码时，修改其值为总页码
        if (this.pageNum > getPages()) {
            this.pageNum = getPages();
        }
    }

    /**
     * 是否启用repair方法,@link{this#repair()}
     *
     * @return
     */
    public boolean isRepair() {
        return repair;
    }

    public void setRepair(boolean repair) {
        this.repair = repair;
    }

    /**
     * @return 当前页码
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @return 每页数据集(最大)大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @return 总页码
     */
    public int getPages() {
        this.pages = (int) (total / pageSize);
        if (total % pageSize != 0) {
            pages++;
        }
        return pages;
    }

    /**
     * @return 当前页面的数据集实际大小
     */
    public int getSize() {
        return data.size();
    }

    /**
     * 上一个有效页码
     *
     * @return 当前页的上一页的页码，如果当前页为第一页时返回1
     */
    public int getPrev() {
        return (pageNum > 1 ? (pageNum - 1) : 1);
    }

    /**
     * 下一个有效页码
     *
     * @return 当前页的下一页的页码，如果当前页 为最后一页时返回最后一页
     */
    public int getNext() {
        int tp = getPages();
        return (pageNum < tp ? (pageNum + 1) : tp);
    }

    /**
     * @return 总数据集大小
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总数据集大小
     *
     * @param total 总数据集大小
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return 当前数据的第一条在所有数据总的位置
     */
    public int getStartIndex() {
        this.startIndex = (getPageNum() - 1) * getPageSize();
        return startIndex >= 0 ? startIndex : 0;
    }

    /**
     * 设置当前数据的第一条在所有数据总的位置
     * <p>一般情况不会使用</p>
     *
     * @param startIndex
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return 获取当前页面的数据集内容
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 设置当前页面的数据集内容
     *
     * @param data 数据集内容
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 为当前页面的数据集添加内容
     *
     * @param mc 要添加的数据集内容
     */
    public void addContent(T mc) {
        data.add(mc);
    }

    /**
     * 是否统计总数(默认开启)
     */
    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
