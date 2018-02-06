package com.wf2311.jfeng.file;

/**
 * @author wf2311
 * @date 2016/06/23 11:35.
 */
public enum SizeType {

    B("B", 1L),
    KB("KB", 1024L),
    MB("MB", 1024 * 1024L),
    GB("GB", 1024 * 1024 * 1024L),
    TB("TB", 1024 * 1024 * 1024 * 1024L),
    PB("PB", 1024 * 1024 * 1024 * 1024 * 1024L);

    private String name;
    private long unit;

    SizeType(String name, Long util) {
        this.name = name;
        this.unit = util;
    }

    public Long getUnit() {
        return unit;
    }
}
