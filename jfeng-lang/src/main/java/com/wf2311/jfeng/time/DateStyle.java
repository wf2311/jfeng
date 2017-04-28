package com.wf2311.jfeng.time;

import java.time.*;
import java.util.Arrays;

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

    private final String value;

    private final boolean showOnly;

    private final Type type;

    private final String regex;

    private final String strictRegex;

    DateStyle(String value, Type type, boolean showOnly) {
        this.value = value;
        this.showOnly = showOnly;
        this.type = type;
        this.regex = _regex1();
        this.strictRegex = _regex2();
    }

    public final String value() {
        return value;
    }

    public final boolean showOnly() {
        return showOnly;
    }

    public final Type type() {
        return type;
    }

    public final String regex() {
        return regex;
    }

    public final String strictRegex() {
        return strictRegex;
    }

    public String _regex1() {
        String r = value.replace("yyyy", YEAR)
                .replace("MM", MONTH)
                .replace("dd", DAY)
                .replace("HH", HOUR)
                .replace("mm", MINUTE)
                .replace("ss", SECOND);
        return "^(" + r + ")$";
    }

    public String _regex2() {
        String r = value.replace("yyyy", STRICT_YEAR)
                .replace("MM", STRICT_MONTH)
                .replace("dd", STRICT_DAY)
                .replace("HH", STRICT_HOUR)
                .replace("mm", STRICT_MINUTE)
                .replace("ss", STRICT_SECOND);
        return "^(" + r + ")$";
    }

    private static final String YEAR = "(\\d{4})";
    private static final String MONTH = "(\\d{1,2})";
    private static final String DAY = "(\\d{1,2})";
    private static final String HOUR = "(\\d{1,2})";
    private static final String MINUTE = "(\\d{1,2})";
    private static final String SECOND = MINUTE;

    private static final String STRICT_YEAR = "(\\d{4})";
    private static final String STRICT_MONTH = "(0?[1-9]|1[012])";
    private static final String STRICT_DAY = "(0?[1-9]|[12][0-9]|3[01])";
    private static final String STRICT_HOUR = "([0-9]|[01][0-9]|2[0-4])";
    private static final String STRICT_MINUTE = "([0-9]|[0-5][0-9])";
    private static final String STRICT_SECOND = STRICT_MINUTE;

    public enum Type {
        DATETIME(LocalDateTime.class),
        DATE(LocalDate.class),
        TIME(LocalTime.class),
        YEAR_MONTH(YearMonth.class),
        MONTH_DAY(MonthDay.class);

        private Class value;

        Type(Class value) {
            this.value = value;
        }

        public Object value() {
            return value;
        }

        public static Type find(Object value) {
            return Arrays.stream(Type.values())
                    .filter(t -> t.value.equals(value))
                    .findAny().orElse(null);
        }
    }
}