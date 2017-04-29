package com.wf2311.jfeng.time;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wf2311.jfeng.time.DateStyle.Type;
import static com.wf2311.jfeng.time.DateStyle.Part;

/**
 * java8 时间工具类
 *
 * @author wf2311
 * @date 2017/04/20 22:12.
 */
public final class DateHelper {

    /**
     * 默认格式样式： {@link DateStyle#YYYY_MM_DD_HH_MM_SS}
     */
    public static final DateStyle DEFAULT_FORMATTER_STYLE = DateStyle.YYYY_MM_DD_HH_MM_SS;

    /**
     * 默认转换格式
     */
    public static final DateTimeFormatter DEFAULT_FORMATTER = formatter(DEFAULT_FORMATTER_STYLE);

    private DateHelper() {
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
        Type type = Type.find(support);
        if (type == null) {
            throw new IllegalArgumentException("不支持的转换类型：" + support);
        }
        return type;
    }

    /**
     * 锁对象
     */
    private static final Object lock = new Object();

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();


    private static SimpleDateFormat sdf(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (lock) {
                dateFormat = new SimpleDateFormat(pattern);
                dateFormat.setLenient(false);
                threadLocal.set(dateFormat);
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }


    /**
     * 从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     *
     * @see DateHelper#styleWithPattern(String)
     * @see DateHelper#styleWithTypeAndPattern(String)
     * @see DateHelper#styleWithRegex(String)
     * @see DateHelper#styleWithStrictRegex(String)
     * <strong>
     * 采用{@link DateHelper#styleWithStrictRegex(String)}进行格式匹配
     * </strong>
     */
    public static DateStyle style(String text) {
        return styleWithStrictRegex(text);
    }

    /**
     * 通过日期格式从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     * <strong>
     * 此方法转换效率高，但对于一些特定格式无法准确匹配
     * </strong>
     */
    public static DateStyle styleWithPattern(String text) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        return Arrays.stream(DateStyle.values())
                .filter(style -> {
                    if (style.showOnly()) {
                        return false;
                    }
                    try {
                        ParsePosition pos = new ParsePosition(0);
                        Date dateTmp = sdf(style.value()).parse(text, pos);
                        if (dateTmp != null && pos.getIndex() == text.length()) {
                            return true;
                        }
                    } catch (Exception ignored) {
                    }
                    return false;
                })
                .findAny().orElse(null);
    }

    /**
     * 通过日期类型和格式从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     * <strong>
     * 此方法转换时间要5倍于其他方法
     * </strong>
     *
     * @see DateStyle.Type
     */
    public static DateStyle styleWithTypeAndPattern(String text) {
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

    /**
     * 通过正则表达式从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     * <strong>
     * 只对时间单位的长度限制进行匹配，不检验取值范围有效性。例如：
     * <pre>
     *     DateStyle.YYYY_MM_DD.equal(styleWithRegex("2017-04-39"))==true;
     * </pre>
     * </strong>
     *
     * @see DateStyle#regex()
     * @see DateStyle.Regex
     */
    public static DateStyle styleWithRegex(String text) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        return Arrays.stream(DateStyle.values())
                .filter(style -> !style.showOnly() && text.matches(style.regex()))
                .findAny().orElse(null);
    }

    /**
     * 通过严格的正则表达式从{@link DateStyle}中匹配时间格式。如果不存在，返回<code>null</code>
     * <strong>
     * 此方法同时对时间单位的长度限制和取值范围进行匹配。例如：
     * <pre>
     *     DateStyle.YYYY_MM_DD.equal(styleWithRegex("2017-04-39"))==false;
     *     DateStyle.YYYY_MM_DD.equal(styleWithRegex("2017-4-19"))==true;
     * </pre>
     * </strong>
     *
     * @see DateStyle#strictRegex()
     * @see DateStyle.StrictRegex
     */
    public static DateStyle styleWithStrictRegex(String text) {
        if (text == null || "".equals(text.trim())) {
            return null;
        }
        return Arrays.stream(DateStyle.values())
                .filter(style -> !style.showOnly() && text.matches(style.regex()) && text.matches(style.strictRegex()))
                .findAny().orElse(null);
    }

    private static DateStyle _style0(Type type, Predicate<DateStyle> predicate) {
        return Arrays.stream(DateStyle.values())
                .parallel()
                .filter(style -> {
                    if (style.showOnly() || !type.equals(style.type())) {
                        return false;
                    }
                    try {
                        return predicate.test(style);
                    } catch (Exception ignored) {
                    }
                    return false;
                })
                .findAny().orElse(null);
    }

    //========================================格式转换=====================================

    private static LocalDateTime parse(String text, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(text, formatter);
        } catch (Exception ignore) {
            return null;
        }
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, String pattern) {
        return parse(text, formatter(pattern));
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     */
    public static LocalDateTime parse(String text, DateStyle style) {
        return parse(text, formatter(style));
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换或者不是<strong>预设的日期时间格式</strong>，返回<code>null</code>
     * <p>
     * <strong>预设的日期时间格式</strong>是指
     * {@link DateStyle#type()} == {@link Type#DATETIME}
     * </p>
     */
    public static LocalDateTime parseStrict(String text) {
        DateStyle style = style(text);
        if (style == null || !Type.DATETIME.equals(style.type())) {
            return null;
        }
        return parse(text, style);
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @throws IllegalArgumentException
     */
    public static LocalDateTime parse(String text) {
        DateStyle style = style(text);
        if (style == null) {
            return null;
        }
        Object cls = style.type().value();
        return parse(text, style, cls);
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
     * @throws IllegalArgumentException
     */
    @Deprecated
    public static <T> LocalDateTime parse(String text, T t) {
        DateStyle style = style(text);
        return parse(text, style, t);
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
     * @throws IllegalArgumentException
     */
    public static <T> LocalDateTime parse(String text, DateStyle style, T t) {
        return parse(text, style.value(), t);
    }

    /**
     * 将时间字符串{@link String}转为{@link LocalDateTime}。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
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
     * 将时间字符串{@link String}转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
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
     * 将时间字符串{@link String}转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
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
     * 将时间字符串{@link String}转为指定时间类型。如果无法转换，返回<code>null</code>
     *
     * @param <T> 取值{@link Type#value()}
     * @throws IllegalArgumentException
     */
    public static <T> T parseToObject(String text, DateStyle style, T t) {
        return parseToObject(text, style.value(), t);
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串{@link String}。默认格式{@link #DEFAULT_FORMATTER_STYLE}
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串{@link String}
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(formatter(pattern));
    }

    /**
     * 将{@link LocalDateTime}格式化为字符串{@link String}
     */
    public static String format(LocalDateTime dateTime, DateStyle style) {
        return dateTime.format(formatter(style));
    }

    public static LocalDateTime parseByRegex(String text) {

        DateStyle style = style(text);
        int year = Year.now().getValue();
        int month = 1;
        int day = 1;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int[] timeParts = style.strictLength() ? timePartStrict(text, style) : timePart(text, style);
        if (timeParts == null || timeParts.length != style.contains().size()) {
            throw new RuntimeException("转换错误");
        }
        for (int i = 0; i < style.contains().size(); i++) {
            switch (style.contains().get(i)) {
                case YEAR:
                    year = timeParts[i];
                    break;
                case MONTH:
                    year = timeParts[i];
                    break;
                case DAY:
                    year = timeParts[i];
                    break;
                case HOUR:
                    year = timeParts[i];
                    break;
                case MINUTE:
                    year = timeParts[i];
                    break;
                case SECOND:
                    year = timeParts[i];
                    break;
                default:
                    break;
            }
        }
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private static int[] timePart(String text, DateStyle style) {
        Matcher matcher = Pattern.compile(style.strictRegex()).matcher(text);
        if (matcher.find()) {
            int[] part = new int[matcher.groupCount()];
            for (int i = 0; i < matcher.groupCount(); i++) {
                part[i] = Integer.valueOf(matcher.group(i + 1));
            }
            return part;
        }
        return null;
    }

    private static int[] timePartStrict(String text, DateStyle style) {
        if (!style.strictLength()) {
            throw new IllegalArgumentException("非法参数");
        }
        int start = 0;
        int end = 0;
        List<Integer> list = new ArrayList<>();
        for (Part part : Part.values()) {
            if (style.value().contains(part.value())) {
                end += part.value().length();
                list.add(Integer.valueOf(text.substring(start, end)));
                start = end + 1;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    //======================================格式转换 结束===================================

    //========================================时间变更=====================================

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
    //======================================时间变更 结束===================================


    //========================================类型转换=====================================

    /**
     * {@link Date}转{@link LocalDateTime}
     */
    public static LocalDateTime ofDate(Date date) {
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
    //======================================类型转换 结束===================================
}
