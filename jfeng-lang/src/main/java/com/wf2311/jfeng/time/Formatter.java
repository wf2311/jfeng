package com.wf2311.jfeng.time;

import java.util.List;

/**
 * @author wangfeng
 * @time 2017/05/02 13:19.
 */
public class Formatter {
    /**
     * 格式
     */
    private final String value;

    /**
     * 是否只用于转换格式化
     */
    private final boolean formatOnly;

    /**
     * 日期类别
     */
    private final DateStyle.Type type;

    /**
     * 匹配规则
     * <p>
     * 只对时间单位的长度限制进行匹配，不检验取值范围有效性。例如
     * 2017-04-01可以匹配{@link DateStyle#YYYYMMDD}
     * 2017-04-41也可以匹配{@link DateStyle#YYYYMMDD}
     * </p>
     *
     * @see DateStyle.Regex
     */
    private final String regex;

    /**
     * 严格的匹配规则
     * <p>
     * 同时对时间单位的长度限制和取值范围进行匹配。例如
     * 2017-04-01可以匹配{@link DateStyle#YYYYMMDD}
     * 2017-04-41无法匹配{@link DateStyle#YYYYMMDD}
     * </p>
     *
     * @see DateStyle.StrictRegex
     */
    private final String strictRegex;

    /**
     * 长度是否严格
     * <p>
     * 此参数用于时间单元没有分隔符的匹配，例如：
     * {@link DateStyle#YYYYMMDD}的<code>strictRegex=true</code>,其值的长度必须为8，20170401匹配成功，而201741无法匹配；
     * {@link DateStyle#YYYY_MM_DD}的<code>strictRegex=false</code>,其值的长度不固定，2017-04-01匹配成功，2017-4-1也可以匹配成功；
     * </p>
     */
    private final boolean strictLength;


    private final List<DateStyle.Part> contains;

    public Formatter(String value, boolean formatOnly, DateStyle.Type type, String regex, String strictRegex, boolean strictLength, List<DateStyle.Part> contains) {
        this.value = value;
        this.formatOnly = formatOnly;
        this.type = type;
        this.regex = regex;
        this.strictRegex = strictRegex;
        this.strictLength = strictLength;
        this.contains = contains;
    }
}
