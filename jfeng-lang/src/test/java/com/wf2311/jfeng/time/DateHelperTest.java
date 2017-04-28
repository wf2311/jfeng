package com.wf2311.jfeng.time;

import junit.framework.TestCase;
import org.junit.Assert;

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
public class DateHelperTest extends TestCase {

    public void test_style() {
        Assert.assertEquals(DateStyle.YYYY_MM_DD_HH_MM_SS, DateHelper.style("2017-04-27 21:29:32"));
        Assert.assertEquals(DateStyle.SLASH_YYYY_MM_DD, DateHelper.style("2017/04/27"));
        Assert.assertEquals(DateStyle.YYYY_MM_DD, DateHelper.style("2017-04-27"));
        Assert.assertEquals(DateStyle.SLASH_YYYY_MM_DD_HH_MM_SS, DateHelper.style("2017/04/27 21:29:32"));
        Assert.assertEquals(DateStyle.YYYY_MM, DateHelper.style("2017-04"));
        Assert.assertEquals(DateStyle.YYYY_MM_DD_HH_MM, DateHelper.style("2017-04-27 21:29"));
        Assert.assertEquals(DateStyle.YYYY_MM_DD_HH, DateHelper.style("2017-04-27 21"));
        Assert.assertEquals(DateStyle.YYYYMM, DateHelper.style("201704"));
        Assert.assertEquals(DateStyle.YYYYMMDD, DateHelper.style("20170427"));
        Assert.assertEquals(DateStyle.YYYYMMDDHH, DateHelper.style("2017042721"));
        Assert.assertEquals(DateStyle.YYYY_MM_DD_HH_MM, DateHelper.style("2015-10-11 14:14"));
        Assert.assertEquals(DateStyle.MM_DD, DateHelper.style("10-11"));
    }

    public void test_style1() {
        IntStream.range(0, 1).parallel().forEach(
                i -> TimeConsts.map.forEach((key, value) -> Assert.assertEquals(key, DateHelper.style(value))));
    }

    public void testGetDateStyle() throws Exception {
        IntStream.range(0, 100000).parallel().forEach(
                i -> TimeConsts.map.forEach((key, value) -> Assert.assertEquals(key, DateHelper.style(value))));
    }


    public void testDateToLocalDate() throws Exception {
        LocalDate result = DateHelper.dateToLocalDate(new GregorianCalendar(2017, Calendar.APRIL, 21, 15, 28).getTime());
        Assert.assertEquals(LocalDate.of(2017, java.time.Month.APRIL, 21), result);
    }

    public void testLocalDateTimeToDate() throws Exception {
        Date result = DateHelper.toDate(LocalDateTime.of(2017, Month.APRIL, 21, 15, 28, 24));
        Assert.assertEquals(new GregorianCalendar(2017, Calendar.APRIL, 21, 15, 28).getTime(), result);
    }

    public void test_parse() {
        String text = "2017年04月21日";
        Assert.assertEquals(null, DateHelper.parseStrict(text));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21,0,0,0), DateHelper.parse(text));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21,0,0,0), DateHelper.parse(text, LocalDate.class));
        Assert.assertEquals(LocalDate.of(2017, 4, 21), DateHelper.parseToObject(text, LocalDate.class));
    }

    public void test_parse2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateStyle.CN_YYYY_MM_DD_HH_MM_SS.value());
        formatter.withResolverStyle(ResolverStyle.LENIENT);
        LocalDateTime parse = LocalDateTime.parse("2017年04月21日 16:35:10", formatter);
        System.out.println(DateHelper.format(parse));
    }

    public void test_parse3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateStyle.CN_YYYY_MM_DD.value());
//        formatter.withResolverStyle(ResolverStyle.LENIENT);
        formatter.withZone(ZoneId.systemDefault());
        LocalDate parse = LocalDate.parse(t1, formatter);
        System.out.println(DateHelper.format(parse.atTime(LocalTime.MIN), "MM月-d日"));
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
        LocalDateTime startOfYear = DateHelper.startOfYear(DT);
        Assert.assertEquals(LocalDateTime.of(LocalDate.of(2017, 1, 1), LocalTime.ofNanoOfDay(0)), startOfYear);
    }

    public void test_startOfYear1() {
        String s = DateHelper.startOfYear(t1);
//        Assert.assertEquals("2017年01月01日", s);
        Assert.assertEquals("2017-01-01", s);
    }


    public void test_endOfYear() {
        LocalDateTime endOfYear = DateHelper.endOfYear(DT);
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


    public void test_parse4() {
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDate.of(2017, 4, 21), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.SLASH_YYYY_MM_DD), LocalDate.class));
        Assert.assertEquals(LocalDate.of(2017, 4, 21), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYY_MM_DD), LocalDate.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.SLASH_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(YearMonth.of(2017, 4), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYY_MM), YearMonth.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 0, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYY_MM_DD_HH), LocalDateTime.class));
        Assert.assertEquals(YearMonth.of(2017, 4), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMM), YearMonth.class));
        Assert.assertEquals(LocalDate.of(2017, 4, 21), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMMDD), LocalDate.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 0, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMMDDHH), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMMDDHHMM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMMDDHHMMSS), LocalDateTime.class));
        Assert.assertEquals(LocalDate.of(2017, 4, 21), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.SLASH_YYYY_MM_DD), LocalDate.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 0, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.SLASH_YYYY_MM_DD_HH), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.SLASH_YYYY_MM_DD_HH_MM), LocalDateTime.class));

        Assert.assertEquals(YearMonth.of(2017, 4), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMM), YearMonth.class));
//        Assert.assertEquals(LocalDateTime.of(2017, 4,21,13,27,0),DateTimeUtils.parseToObject(TimeConsts.map.get(FormatterStyle.CN2_2_YYYY_MM_DD_HH),LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_2_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_3_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_2_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_3_YYYY_MM_DD_HH_MM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 0), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.YYYYMMDDHHMM), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_2_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN_3_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_2_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
        Assert.assertEquals(LocalDateTime.of(2017, 4, 21, 13, 27, 59), DateHelper.parseToObject(TimeConsts.map.get(DateStyle.CN2_3_YYYY_MM_DD_HH_MM_SS), LocalDateTime.class));
    }

    public void test21() {
        String text = TimeConsts.map.get(DateStyle.CN_YYYY_MM_DD_HH_MM);
        System.out.println(text);
        LocalDateTime parse = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DateStyle.CN2_2_MM_DD_HH_MM.value()));
        System.out.println(parse);
    }

    public void test_toYearMonth() {
        Assert.assertEquals("2017-04", DateHelper.toYearMonth(LocalDateTime.now()).toString());
    }

}

