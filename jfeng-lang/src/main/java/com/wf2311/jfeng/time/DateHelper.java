package com.wf2311.jfeng.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * java8 时间工具类
 *
 * @author wf2311
 */
public final class DateHelper {

    /**
     * 默认格式样式： {@link DateStyle#YYYY_MM_DD_HH_MM_SS}
     */
    public static final DateStyle DEFAULT_FORMATTER_STYLE = DateStyle.YYYY_MM_DD_HH_MM_SS;

    /**
     * 默认格式
     */
    public static final DateTimeFormatter DEFAULT_FORMATTER = formatter(DEFAULT_FORMATTER_STYLE);

    private final static Map<Object, Type> support = new HashMap<>();

    private DateHelper() {
    }

    static {
        support.put(LocalDate.class, Type.DATE);
        support.put(LocalDateTime.class, Type.DATETIME);
        support.put(LocalTime.class, Type.TIME);
        support.put(MonthDay.class, Type.MONTH_DAY);
        support.put(YearMonth.class, Type.YEAR_MONTH);
    }

    /**
     * 创建{@link DateTimeFormatter}
     */
    public static DateTimeFormatter formatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.LENIENT);
        return formatter;
    }

    /**
     * 创建{@link DateTimeFormatter}
     */
    public static DateTimeFormatter formatter(DateStyle dateStyle) {
        return formatter(dateStyle.value());
    }


    private static Type type(Object support) {
        Type type = DateHelper.support.get(support);
        if (type == null) {
            throw new IllegalArgumentException("不支持的转换类型：" + support);
        }
        return type;
    }

    /**
     * 从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     */
    public static DateStyle style(String text) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        DateStyle style;
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

    private static DateStyle _style0(Type type, Predicate<DateStyle> predicate) {
        return Arrays.stream(DateStyle.values())
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
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, String pattern) {
        try {
            return LocalDateTime.parse(text, formatter(pattern));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, DateStyle style) {
        try {
            return LocalDateTime.parse(text, formatter(style));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text) {
        DateStyle style = style(text);
        if (style == null || !Type.DATETIME.equals(style.type())) {
            return null;
        }
        return parse(text, style);
    }

    /**
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> LocalDateTime parse(String text, T t) {
        DateStyle style = style(text);
        return parse(text, style, t);
    }

    /**
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> LocalDateTime parse(String text, DateStyle style, T t) {
        return parse(text, style.value(), t);
    }

    /**
     * 将时间字符串转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> LocalDateTime parse(String text, String pattern, T t) {
        Type type = type(t);
        try {
            switch (type) {
                case DATE:
                    LocalDate localDate = LocalDate.parse(text, formatter(pattern));
                    return ofLocalDate(localDate);
                case DATETIME:
                    return LocalDateTime.parse(text, formatter(pattern));
                case TIME:
                    LocalTime localTime = LocalTime.parse(text, formatter(pattern));
                    return ofLocalTime(localTime);
                case MONTH_DAY:
                    MonthDay monthDay = MonthDay.parse(text, formatter(pattern));
                    return ofMonthDay(monthDay);
                case YEAR_MONTH:
                    YearMonth yearMonth = YearMonth.parse(text, formatter(pattern));
                    return ofYearMonth(yearMonth);
                default:
                    return null;
            }
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 将时间字符串转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> T parseToObject(String text, T t) {
        DateStyle style = style(text);
        if (style == null) {
            return null;
        }
        return parseToObject(text, style, t);
    }

    /**
     * 将时间字符串转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> T parseToObject(String text, String pattern, T t) {
        Type type = type(t);
        try {
            switch (type) {
                case DATE:
                    return (T) LocalDate.parse(text, formatter(pattern));
                case DATETIME:
                    return (T) LocalDateTime.parse(text, formatter(pattern));
                case TIME:
                    return (T) LocalTime.parse(text, formatter(pattern));
                case MONTH_DAY:
                    return (T) MonthDay.parse(text, formatter(pattern));
                case YEAR_MONTH:
                    return (T) YearMonth.parse(text, formatter(pattern));
                default:
                    return null;
            }
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 将时间字符串转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link DateHelper#support}
     * @throws IllegalArgumentException
     */
    public static <T> T parseToObject(String text, DateStyle style, T t) {
        return parseToObject(text, style.value(), t);
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
    public static String format(LocalDateTime dateTime, DateStyle style) {
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
        DateStyle style = style(text);
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
    private static LocalDateTime ofDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    /**
     * {@link LocalDateTime}转{@link Date}
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * {@link LocalDate}转{@link LocalDateTime}
     *
     * @return 当天的开始时刻
     */
    public static LocalDateTime ofLocalDate(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    /**
     * {@link LocalDateTime}转{@link LocalDate}
     */
    public static LocalDate toLocalDate(LocalDateTime dateTime) {
        return dateTime.toLocalDate();
    }

    /**
     * {@link YearMonth}转{@link LocalDateTime}
     *
     * @return 所在月开始时刻
     */
    public static LocalDateTime ofYearMonth(YearMonth yearMonth) {
        return LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
    }

    /**
     * {@link LocalDateTime}转{@link YearMonth}
     */
    public static YearMonth toYearMonth(LocalDateTime dateTime) {
        return YearMonth.of(dateTime.getYear(), dateTime.getMonth());
    }

    /**
     * {@link MonthDay}转{@link LocalDateTime}
     *
     * @return 当前年所在月日的开始时刻
     */
    public static LocalDateTime ofMonthDay(MonthDay monthDay) {
        return LocalDateTime.of(monthDay.atYear(LocalDateTime.now().getYear()), LocalTime.MIN);
    }

    /**
     * {@link LocalDateTime}转{@link MonthDay}
     */
    public static MonthDay toMonthDay(LocalDateTime dateTime) {
        return MonthDay.of(dateTime.getMonth(), dateTime.getDayOfMonth());
    }

    /**
     * {@link YearMonth}转{@link LocalDateTime}
     *
     * @return 当天的所在时刻
     */
    public static LocalDateTime ofLocalTime(LocalTime time) {
        return LocalDateTime.of(LocalDate.now(), time);
    }

    /**
     * {@link LocalDateTime}转{@link LocalTime}
     */
    public static LocalTime toLocalTime(LocalDateTime dateTime) {
        return dateTime.toLocalTime();
    }

    /**
     * {@link LocalDate}转{@link Date}
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}