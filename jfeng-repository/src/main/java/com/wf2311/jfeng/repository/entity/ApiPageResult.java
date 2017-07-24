package com.wf2311.jfeng.repository.entity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * api返回基类 包含分页
 *
 * @author wf2311
 */
public class ApiPageResult<T> {
    /**
     * 默认每页数据集大小
     */
    private static final int PAGE_SIZE = 10;

    /**
     * 当pageSize小于0时,为查询所有
     */
    private int pageSize = PAGE_SIZE;

    @ApiModelProperty(name = "返回结果代号", required = true, notes = "主要用于表示返回失败的原因类别")
    private int code = 0;

    @ApiModelProperty(name = "返回结果", required = true, notes = "true:成功;false:失败;")
    private boolean success = true;

    @ApiModelProperty(name = "返回信息")
    private String message;

    @ApiModelProperty(name = "返回结果主体")
    private List<T> data;

    @ApiModelProperty(name = "查询起始页码")
    private int pageNum = 1;

    @ApiModelProperty(name = "符合查询条件数据总数")
    private long total;

    @ApiModelProperty(name = "总页数")
    private int pages;


    public ApiPageResult() {
    }

    public ApiPageResult(int pageNum, int pageSize, int total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public ApiPageResult(Pager<T> pager) {
        if (pager == null) {
            return;
        }
        this.pageNum = pager.getPageNum();
        this.pageSize = pager.getPageSize();
        this.total = pager.getPages();
        this.data = pager.getData();
    }

    public ApiPageResult(Page<T> page) {
        if (page == null) {
            return;
        }
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getPages();
        this.data = page.getResult();
    }


    public ApiPageResult(PageInfo<T> page) {
        if (page == null) {
            return;
        }
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getPages();
        this.data = page.getList();
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public int getPages() {
        this.pages = (int) (total / pageSize);
        if (total % pageSize != 0) {
            pages++;
        }
        return pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
