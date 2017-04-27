package com.wf2311.jfeng.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;

/**
 * @author wf2311
 * @time 2017/04/11 13:19.
 */
public final class DateTimeUtils {

    /**
     * 默认格式样式： {@link FormatterStyle#YYYY_MM_DD_HH_MM_SS}
     */
    private static final FormatterStyle DEFAULT_FORMATTER_STYLE = FormatterStyle.YYYY_MM_DD_HH_MM_SS;

    /**
     * 默认格式
     */
    private static final DateTimeFormatter DEFAULT_FORMATTER = formatter(DEFAULT_FORMATTER_STYLE);

    private DateTimeUtils() {
    }

    private static DateTimeFormatter formatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        formatter.withResolverStyle(ResolverStyle.LENIENT);
        return formatter;
    }

    private static DateTimeFormatter formatter(FormatterStyle dateStyle) {
        return formatter(dateStyle.value());
    }


    /**
     * 获取时间字符串的格式。如果在与设定的格式{@link FormatterStyle}中不存在，返回<code>null</code>
     */
    public static FormatterStyle style(String text) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        FormatterStyle style;
        style = _style0(Type.DATETIME, s -> LocalDateTime.parse(text, formatter(s)) != null);
        if (style != null) {
            return style;
        }
        style = _style0(Type.DATE, s -> LocalDate.parse(text, formatter(s)) != null);
        if (style != null) {
            return style;
        }
        style = _style0(Type.TIME, s -> LocalTime.parse(text, formatter(s)) != null);
        if (style != null) {
            return style;
        }
        style = _style0(Type.YEAR_MONTH, s -> YearMonth.parse(text, formatter(s)) != null);
        if (style != null) {
            return style;
        }
        style = _style0(Type.MONTH_DAY, s -> MonthDay.parse(text, formatter(s)) != null);
        if (style != null) {
            return style;
        }
        return null;
    }

    private static FormatterStyle _style0(Type type, Predicate<FormatterStyle> predicate) {
        return Arrays.stream(FormatterStyle.values())
                .filter(style -> {
                    if (style.showOnly() || !type.equals(style.type())) {
                        return false;
                    }
                    try {
                        return predicate.test(style);
                    } catch (Exception ignored) {
                    }
                    return false;
                }).findAny()
                .orElse(null);
    }

    /**
     * 将时间字符串转化为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, String pattern) {
        try {
            return LocalDateTime.parse(text, formatter(pattern));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间字符串转化为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, FormatterStyle style) {
        try {
            return LocalDateTime.parse(text, formatter(style));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间字符串转化为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text) {
        FormatterStyle style = style(text);
        if (style == null || !Type.DATETIME.equals(style.type())) {
            return null;
        }
        return parse(text, style);
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串。默认格式{@link #DEFAULT_FORMATTER_STYLE}
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(formatter(pattern));
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串
     */
    public static String format(LocalDateTime dateTime, FormatterStyle style) {
        return dateTime.format(formatter(style));
    }

    /**
     * 所在日期的同年开始时刻
     */
    public static LocalDateTime startOfYear(LocalDateTime dateTime) {
        return LocalDateTime.of(LocalDate.of(dateTime.getYear(), 1, 1), LocalTime.MIN);
    }

    /**
     * 所在日期的同年开始时刻
     */
    public static String startOfYear(String text) {
        FormatterStyle style = style(text);
        return format(startOfYear(parse(text, style)), style);
    }


    /**
     * 所在日期的同年结束时刻
     */
    public static LocalDateTime endOfYear(LocalDateTime dateTime) {
        return LocalDateTime.of(LocalDate.of(dateTime.getYear(), 12, 31), LocalTime.MAX);
    }

    /**
     * 所在日期的同月开始时刻
     */
    public static LocalDateTime startOfMonth(LocalDateTime dateTime) {
        return LocalDateTime.of(LocalDate.of(dateTime.getYear(), dateTime.getMonth(), 1), LocalTime.MIN);
    }

    /**
     * 所在日期的同月结束时刻
     */
    public static LocalDateTime endOfMonth(LocalDateTime dateTime) {
        return startOfMonth(dateTime).plusMonths(1).minusNanos(1);
    }


    //===========================================================================

    /**
     * {@link Date}转{@link LocalDateTime}
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * {@link LocalDate}转{@link Date}
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * {@link LocalDateTime}转{@link Date}
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
