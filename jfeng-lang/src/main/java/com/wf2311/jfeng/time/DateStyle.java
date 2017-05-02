package com.wf2311.jfeng.time;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 日期格式
 * <strong>
 * 将最常用的转换格式放在最前面，可以提高{@link DateHelper#style(String)}的 转换效率
 * </strong>
 *
 * @author wf2311
 * @date 2017/04/20 22:15.
 */
public enum DateStyle {

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", Type.DATETIME, false),

    SLASH_YYYY_MM_DD("yyyy/MM/dd", Type.DATE, false),

    YYYY_MM_DD("yyyy-MM-dd", Type.DATE, false),

    SLASH_YYYY_MM_DD_HH_MM_SS("yyyy/MM/dd HH:mm:ss", Type.DATETIME, false),

    YYYY_MM("yyyy-MM", Type.YEAR_MONTH, false),

    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", Type.DATETIME, false),

    YYYY_MM_DD_HH("yyyy-MM-dd HH", Type.DATETIME, false),

    YYYYMM("yyyyMM", Type.YEAR_MONTH, false, true),

    YYYYMMDD("yyyyMMdd", Type.DATE, false, true),

    YYYYMMDDHH("yyyyMMddHH", Type.DATETIME, false, true),

    YYYYMMDDHHMM("yyyyMMddHHmm", Type.DATETIME, false, true),

    YYYYMMDDHHMMSS("yyyyMMddHHmmss", Type.DATETIME, false, true),

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

    CN_HH_MM_SS("HH时mm分ss秒", Type.TIME, false),

    CN_HH_MM("HH时mm分", Type.TIME, false),

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

    CN2_3_MM_DD_HH_MM_SS("MM月dd号 HH时mm分ss秒", Type.DATETIME, true),;

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
    private final Type type;

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
    private final boolean lengthStrict;


    private final List<Part> contains;

    DateStyle(String value, Type type, boolean formatOnly) {
        this.value = value;
        this.formatOnly = formatOnly;
        this.type = type;
        this.regex = _regex();
        this.strictRegex = _strictRegex();
        this.lengthStrict = false;
        this.contains = _contains();
    }

    DateStyle(String value, Type type, boolean formatOnly, boolean lengthStrict) {
        this.value = value;
        this.formatOnly = formatOnly;
        this.type = type;
        this.lengthStrict = lengthStrict;
        if (lengthStrict) {
            this.regex = regexNum(value.length());
        } else {
            this.regex = _regex();
        }
        this.strictRegex = _strictRegex();
        this.contains = _contains();
    }

    public final String value() {
        return value;
    }

    public final boolean showOnly() {
        return formatOnly;
    }

    public final Type type() {
        return type;
    }

    public final String regex() {
        return regex;
    }

    public boolean lengthStrict() {
        return lengthStrict;
    }

    public final String strictRegex() {
        return strictRegex;
    }

    public final List<Part> contains() {
        return this.contains;
    }

    private String _regex() {
        String r = value
                .replace(Part.YEAR.value, Regex.YEAR.value)
                .replace(Part.MONTH.value, Regex.MONTH.value)
                .replace(Part.DAY.value, Regex.DAY.value)
                .replace(Part.HOUR.value, Regex.HOUR.value)
                .replace(Part.MINUTE.value, Regex.MINUTE.value)
                .replace(Part.SECOND.value, Regex.SECOND.value);
        return fillRegex(r);
    }

    private String _strictRegex() {
        String r = value
                .replace(Part.YEAR.value, StrictRegex.YEAR.value)
                .replace(Part.MONTH.value, StrictRegex.MONTH.value)
                .replace(Part.DAY.value, StrictRegex.DAY.value)
                .replace(Part.HOUR.value, StrictRegex.HOUR.value)
                .replace(Part.MINUTE.value, StrictRegex.MINUTE.value)
                .replace(Part.SECOND.value, StrictRegex.SECOND.value);
        return fillRegex(r);
    }

    private List<Part> _contains() {
        List<Part> list = new ArrayList<>();
        Arrays.stream(value.split("")).distinct()
                .forEach(s -> {
                    Part part = Part.find(s);
                    if (part != null && !list.contains(part)) {
                        list.add(part);
                    }
                });
        return list;
    }

    private static String regexNum(int size) {
        return fillRegex("\\d{" + size + "}");
    }

    private static String fillRegex(String regex) {
        return "^" + regex + "$";
    }

    /**
     * 时间单元匹配规则
     * <p>
     * 只对时间单位的长度限制进行匹配，不检验取值范围有效性.
     * 年：4位数字
     * 月：1~2位数字
     * 日：1~2位数字
     * 时：1~2位数字
     * 分：1~2位数字
     * 秒：1~2位数字
     * </p>
     */
    public enum Regex {
        YEAR("(\\d{4})"),
        MONTH("(\\d{1,2})"),
        DAY("(\\d{1,2})"),
        HOUR("(\\d{1,2})"),
        MINUTE("(\\d{1,2})"),
        SECOND("(\\d{1,2})");

        private final String value;

        Regex(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    /**
     * 严格的时间单元匹配规则
     * <p>
     * 同时对时间单位的长度限制和取值范围进行匹配
     * 年：4位数字
     * 月：1~2位数字,取值1~12
     * 日：1~2位数字,取值1~31
     * 时：1~2位数字,取值0~24
     * 分：1~2位数字,取值0~59
     * 秒：1~2位数字,取值0~59
     * </p>
     */
    public enum StrictRegex {
        YEAR("(\\d{4})"),
        MONTH("(0?[1-9]|1[012])"),
        DAY("(0?[1-9]|[12][0-9]|3[01])"),
        HOUR("([0-9]|[01][0-9]|2[0-4])"),
        MINUTE("([0-9]|[0-5][0-9])"),
        SECOND("([0-9]|[0-5][0-9])");

        private final String value;

        StrictRegex(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public enum Part {
        YEAR("yyyy"),
        MONTH("MM"),
        DAY("dd"),
        HOUR("HH"),
        MINUTE("mm"),
        SECOND("ss");

        private final String value;

        Part(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

        public static Part find(String value) {
            return Arrays.stream(Part.values())
                    .filter(t -> t.value.contains(value))
                    .findAny().orElse(null);
        }
    }

    public enum Type {
        DATETIME(LocalDateTime.class),
        DATE(LocalDate.class),
        TIME(LocalTime.class),
        YEAR_MONTH(YearMonth.class),
        MONTH_DAY(MonthDay.class);

        private Class<?> value;

        Type(Class<?> value) {
            this.value = value;
        }

        public Class<?> value() {
            return value;
        }

        public static Type find(Object value) {
            return Arrays.stream(Type.values())
                    .filter(t -> t.value.equals(value))
                    .findAny().orElse(null);
        }
    }
}