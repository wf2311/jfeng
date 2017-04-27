package com.wf2311.jfeng.time;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author wangfeng
 * @time 2017/04/21 15:28.
 */
public class DateTimeUtilsTest extends TestCase {

    public void test_style() {
        Assert.assertEquals(FormatterStyle.YYYY_MM_DD_HH_MM_SS, DateTimeUtils.style("2017-04-27 21:29:32"));
        Assert.assertEquals(FormatterStyle.SLASH_YYYY_MM_DD, DateTimeUtils.style("2017/04/27"));
        Assert.assertEquals(FormatterStyle.YYYY_MM_DD, DateTimeUtils.style("2017-04-27"));
        Assert.assertEquals(FormatterStyle.SLASH_YYYY_MM_DD_HH_MM_SS, DateTimeUtils.style("2017/04/27 21:29:32"));
        Assert.assertEquals(FormatterStyle.YYYY_MM, DateTimeUtils.style("2017-04"));
        Assert.assertEquals(FormatterStyle.YYYY_MM_DD_HH_MM, DateTimeUtils.style("2017-04-27 21:29"));
        Assert.assertEquals(FormatterStyle.YYYY_MM_DD_HH, DateTimeUtils.style("2017-04-27 21"));
        Assert.assertEquals(FormatterStyle.YYYYMM, DateTimeUtils.style("201704"));
        Assert.assertEquals(FormatterStyle.YYYYMMDD, DateTimeUtils.style("20170427"));
        Assert.assertEquals(FormatterStyle.YYYYMMDDHH, DateTimeUtils.style("2017042721"));
        Assert.assertEquals(FormatterStyle.YYYY_MM_DD_HH_MM, DateTimeUtils.style("2015-10-11 14:14"));
        Assert.assertEquals(FormatterStyle.MM_DD, DateTimeUtils.style("10-11"));
    }

    public void test_style1() {
        IntStream.range(0, 1).parallel().forEach(
                i -> TimeConsts.map.forEach((key, value) -> Assert.assertEquals(key, DateUtils.getStyle(value))));
    }

    public void testGetDateStyle() throws Exception {
        IntStream.range(0, 100000).parallel().forEach(
                i -> TimeConsts.map.forEach((key, value) -> Assert.assertEquals(key, DateUtils.getStyle(value))));
    }


    public void testDateToLocalDate() throws Exception {
        LocalDate result = DateTimeUtils.dateToLocalDate(new GregorianCalendar(2017, Calendar.APRIL, 21, 15, 28).getTime());
        Assert.assertEquals(LocalDate.of(2017, java.time.Month.APRIL, 21), result);
    }

    public void testLocalDateTimeToDate() throws Exception {
        Date result = DateTimeUtils.localDateTimeToDate(LocalDateTime.of(2017, Month.APRIL, 21, 15, 28, 24));
        Assert.assertEquals(new GregorianCalendar(2017, Calendar.APRIL, 21, 15, 28).getTime(), result);
    }

    public void test_parse() {
        LocalDateTime parse = DateTimeUtils.parse("2017年04月21日", FormatterStyle.CN_YYYY_MM_DD);
        System.out.println(DateTimeUtils.format(parse));
    }

    public void test_parse2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatterStyle.CN_YYYY_MM_DD_HH_MM_SS.value());
        formatter.withResolverStyle(ResolverStyle.LENIENT);
        LocalDateTime parse = LocalDateTime.parse("2017年04月21日 16:35:10", formatter);
        System.out.println(DateTimeUtils.format(parse));
    }

    public void test_parse3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatterStyle.CN_YYYY_MM_DD.value());
//        formatter.withResolverStyle(ResolverStyle.LENIENT);
        formatter.withZone(ZoneId.systemDefault());
        LocalDate parse = LocalDate.parse(t1, formatter);
        System.out.println(DateTimeUtils.format(parse.atTime(LocalTime.MIN), "MM月-d日"));
    }

    private static final LocalDateTime DT = LocalDateTime.of(2017, 4, 21, 12, 59, 59);
    private static String t1 = "2017-04-21";
    private static String t2 = "2017-04-21 19:57:42";


    public void test_addYear() {
        LocalDateTime dateTime = DT.plusYears(1);
        Assert.assertEquals(LocalDateTime.of(2018, 4, 21, 12, 59, 59),
                dateTime);
    }

    public void test_addMonth() {
        LocalDateTime dateTime = DT.plusMonths(1);
        Assert.assertEquals(LocalDateTime.of(2017, 5, 21, 12, 59, 59),
                dateTime);
    }

    public void test_addDay() {
        LocalDateTime dateTime = DT.plusDays(1);
        Assert.assertEquals(LocalDateTime.of(2017, 4, 22, 12, 59, 59),
                dateTime);
    }

    public void test_getYear() {
        int year = DT.getYear();
        Assert.assertEquals(2017, year);
    }

    public void test_getMonth() {
        int month = DT.getMonthValue();
        Assert.assertEquals(4, month);
    }

    public void test_getDayOfYear() {
        int month = DT.getDayOfYear();
        Assert.assertEquals(111, month);
    }

    public void test_getDayOfMonth() {
        int month = DT.getDayOfMonth();
        Assert.assertEquals(21, month);
    }

    public void test_getDayOfWeek() {
        DayOfWeek dayOfWeek = DT.getDayOfWeek();
        String cn = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.CHINA);
        long day = dayOfWeek.getLong(ChronoField.DAY_OF_WEEK);
        Assert.assertEquals("星期五", cn);
        Assert.assertEquals(5, day);
    }

    public void test_startOfYear() {
        LocalDateTime startOfYear = DateTimeUtils.startOfYear(DT);
        Assert.assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.ofNanoOfDay(0)), startOfYear);
    }

    public void test_startOfYear1() {
        String s = DateTimeUtils.startOfYear(t1);
//        Assert.assertEquals("2017年01月01日", s);
        Assert.assertEquals("2017-01-01", s);
    }


    public void test_endOfYear() {
        LocalDateTime endOfYear = DateTimeUtils.endOfYear(DT);
        Assert.assertEquals(LocalDateTime.of(LocalDate.of(2017, 12, 31), LocalTime.MAX), endOfYear);
    }

    public void test1() {
        LocalDateTime parse = LocalDateTime.parse(t1, DateTimeFormatter.ISO_DATE);
        System.out.println(parse);
    }

    public void test_style2() {
        Date date = DateUtils.parseToDate("2015-10");
        System.out.println(date);
    }

}


