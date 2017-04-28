package com.wf2311.jfeng.lang;

import com.wf2311.jfeng.time.DateStyle;
import junit.framework.TestCase;
import org.junit.Assert;
import strman.Strman;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


/**
 * @author wangfeng
 * @time 2017/04/28 16:33.
 */
public class RegexTest extends TestCase {
    public void test_month() {
        IntStream.range(0, 50).forEach(i -> System.out.println(i + "\t" + Pattern.matches(Regexs.month, (i < 10 ? "0" : "") + String.valueOf(i))));
    }

    public void test_dayOfMonth() {
        IntStream.range(0, 50).forEach(i -> System.out.println(i + "\t" + Pattern.matches(Regexs.dayOfMonth, (i < 10 ? "0" : "") + String.valueOf(i))));
    }


    public void test_monthDay() {
        String s = Regexs.month + "-" + Regexs.dayOfMonth;
        s = s.replaceAll("\\^", "\\").replaceAll("$", "");
        System.out.println(s);
//        boolean matches = Pattern.matches(s, "12-31");
//        System.out.println(matches);
    }

    public void test3() {
        String regex1 = "(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
        String regex2 = "(\\d{4})年(0?[1-9]|1[012])月(0?[1-9]|[12][0-9]|3[01])日";
        String regex3 = "(\\d{4})年(0?[1-9]|1[012])月(0?[1-9]|[12][0-9]|3[01])号";
        Assert.assertTrue("2017-04-28".matches(regex1));
        Assert.assertTrue("2017年04月28日".matches(regex2));
        Assert.assertTrue("2017年4月28日".matches(regex2));
        Assert.assertTrue("2017年04月28号".matches(regex3));
    }

    public void test4() {
        Arrays.stream(DateStyle.values()).forEach(style -> {
            String append = Strman.append(style.toString(), "(\"", style.value(), "\",", "Type." + style.type(), ",", style.showOnly() + "", ",\"", style.regex().replace("\\", "\\\\"), "\"),");
            System.out.println(append);
            System.out.println();
        });
    }

    public void test5() {
    }


    public void test_regex_day() {
        match(36, "((\\d{4})年(0?[1-9]|1[012])月(0?[1-9]|[12][0-9]|3[01])日)");
    }

    private void match(int end, String regex) {
        IntStream.range(0, end).forEach(i -> {
            boolean matches = String.valueOf(i).matches(regex);
            System.out.println(i + "\t" + matches);
        });
    }

    public void test11() {
        String regex = DateStyle.YYYYMMDD.regex();
        boolean matches = "20151112".matches(regex);
        System.out.println(regex);
        System.out.println(matches);
    }
}
