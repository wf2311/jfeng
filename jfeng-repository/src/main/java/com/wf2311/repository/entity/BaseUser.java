package com.wf2311.repository.entity;

/**
 * @author wangfeng
 * @time 2017/05/23 15:20.
 */
public class BaseUser {
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}