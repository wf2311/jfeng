package com.wf2311.jfeng.time;

/**
 * 日期格式
 * <strong>
 * 将最常用的转换格式放在最前面，可以提高{@link DateHelper#style(String)}的 转换效率
 * </strong>
 *
 * @author wf2311
 * @date 2016/04/21 13:57.
 */
public enum DateStyle {

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", Type.DATETIME, false),

    SLASH_YYYY_MM_DD("yyyy/MM/dd", Type.DATE, false),

    YYYY_MM_DD("yyyy-MM-dd", Type.DATE, false),

    SLASH_YYYY_MM_DD_HH_MM_SS("yyyy/MM/dd HH:mm:ss", Type.DATETIME, false),

    YYYY_MM("yyyy-MM", Type.YEAR_MONTH, false),

    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", Type.DATETIME, false),

    YYYY_MM_DD_HH("yyyy-MM-dd HH", Type.DATETIME, false),

    YYYYMM("yyyyMM", Type.YEAR_MONTH, false),

    YYYYMMDD("yyyyMMdd", Type.DATE, false),

    YYYYMMDDHH("yyyyMMddHH", Type.DATETIME, false),

    YYYYMMDDHHMM("yyyyMMddHHmm", Type.DATETIME, false),

    YYYYMMDDHHMMSS("yyyyMMddHHmmss", Type.DATETIME, false),

    SLASH_YYYY_MM("yyyy/MM", Type.YEAR_MONTH, false),

    SLASH_YYYY_MM_DD_HH("yyyy/MM/dd HH", Type.DATETIME, false),

    SLASH_YYYY_MM_DD_HH_MM("yyyy/MM/dd HH:mm", Type.DATETIME, false),

    CN_YYYY_MM("yyyy年MM月", Type.YEAR_MONTH, false),

    CN_YYYY_MM_DD("yyyy年MM月dd日", Type.DATE, false),

    CN_YYYY_MM_DD_HH("yyyy年MM月dd日 HH点", Type.DATETIME, false),

    CN_YYYY_MM_DD_HH_MM("yyyy年MM月dd日 HH:mm", Type.DATETIME, false),

    CN_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd日 HH:mm:ss", Type.DATETIME, false),

    CN2_YYYY_MM_DD("yyyy年MM月dd号", Type.DATE, false),

    CN2_YYYY_MM_DD_HH("yyyy年MM月dd号 HH点", Type.DATETIME, false),

    CN2_YYYY_MM_DD_HH_MM("yyyy年MM月dd号 HH:mm", Type.DATETIME, false),

    CN2_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd号 HH:mm:ss", Type.DATETIME, false),

    CN2_2_YYYY_MM_DD_HH("yyyy年MM月dd号 HH时", Type.DATETIME, false),

    CN2_2_YYYY_MM_DD_HH_MM("yyyy年MM月dd号 HH点mm分", Type.DATETIME, false),

    CN2_2_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd号 HH点mm分ss秒", Type.DATETIME, false),

    CN2_3_YYYY_MM_DD_HH_MM("yyyy年MM月dd号 HH时mm分", Type.DATETIME, false),

    CN2_3_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd号 HH时mm分ss秒", Type.DATETIME, false),

    CN_2_YYYY_MM_DD_HH("yyyy年MM月dd日 HH时", Type.DATETIME, false),

    CN_2_YYYY_MM_DD_HH_MM("yyyy年MM月dd日 HH点mm分", Type.DATETIME, false),

    CN_2_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd日 HH点mm分ss秒", Type.DATETIME, false),

    CN_3_YYYY_MM_DD_HH_MM("yyyy年MM月dd日 HH时mm分", Type.DATETIME, false),

    CN_3_YYYY_MM_DD_HH_MM_SS("yyyy年MM月dd日 HH时mm分ss秒", Type.DATETIME, false),

    MM_DD("MM-dd", Type.MONTH_DAY, false),

    MM_DD_HH_MM("MM-dd HH:mm", Type.DATETIME, true),

    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", Type.DATETIME, true),

    HH_MM("HH:mm", Type.TIME, true),

    HH_MM_SS("HH:mm:ss", Type.TIME, false),

    SLASH_MM_DD("MM/dd", Type.MONTH_DAY, false),

    SLASH_MM_DD_HH_MM("MM/dd HH:mm", Type.DATETIME, true),

    SLASH_MM_DD_HH_MM_SS("MM/dd HH:mm:ss", Type.DATETIME, true),

    CN_MM_DD("MM月dd日", Type.MONTH_DAY, false),

    CN2_MM_DD("MM月dd号", Type.MONTH_DAY, false),

    CN_MM_DD_HH_MM("MM月dd日 HH:mm", Type.DATETIME, true),

    CN_2_MM_DD_HH_MM("MM月dd日 HH点mm分", Type.DATETIME, true),

    CN_3_MM_DD_HH_MM("MM月dd日 HH时mm分", Type.DATETIME, true),

    CN2_MM_DD_HH_MM("MM月dd号 HH:mm", Type.DATETIME, true),

    CN2_2_MM_DD_HH_MM("MM月dd号 HH点mm分", Type.DATETIME, true),

    CN2_3_MM_DD_HH_MM("MM月dd号 HH时mm分", Type.DATETIME, true),

    CN_MM_DD_HH_MM_SS("MM月dd日 HH:mm:ss", Type.DATETIME, true),

    CN_2_MM_DD_HH_MM_SS("MM月dd日 HH点mm分ss秒", Type.DATETIME, true),

    CN_3_MM_DD_HH_MM_SS("MM月dd日 HH时mm分ss秒", Type.DATETIME, true),

    CN2_MM_DD_HH_MM_SS("MM月dd号 HH:mm:ss", Type.DATETIME, true),

    CN2_2_MM_DD_HH_MM_SS("MM月dd号 HH点mm分ss秒", Type.DATETIME, true),

    CN2_3_MM_DD_HH_MM_SS("MM月dd号 HH时mm分ss秒", Type.DATETIME, true);

    private String value;

    private boolean showOnly;

    private Type type;

    DateStyle(String value, Type type, boolean isShowOnly) {
        this.value = value;
        this.showOnly = isShowOnly;
        this.type = type;
    }

    public String value() {
        return value;
    }

    public boolean showOnly() {
        return showOnly;
    }

    public Type type() {
        return type;
    }

    public enum Type {
        DATETIME, DATE, TIME, YEAR_MONTH, MONTH_DAY
    }
}