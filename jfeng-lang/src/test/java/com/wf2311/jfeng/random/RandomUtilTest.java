package com.wf2311.jfeng.random;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author wangfeng
 * @time 2018/02/06 10:33.
 */
public class RandomUtilTest {

    @Test
    public void generateDoubles() {
        long count = Arrays.stream(RandomUtil.generateDoubles(1, 10, 1000)).filter(d -> d >= 1 && d < 10).count();
        Assert.assertEquals(1000, count);
    }

    @Test
    public void generateInts() {
        long count = Arrays.stream(RandomUtil.generateInts(1, 10, 1000)).filter(d -> d >= 1 && d < 10).count();
        Assert.assertEquals(1000, count);
    }

    @Test
    public void generateLongs() {
        long count = Arrays.stream(RandomUtil.generateLongs(1, 10, 1000)).filter(d -> d >= 1 && d < 10).count();
        Assert.assertEquals(1000, count);
    }

    @Test
    public void getLocalTimes() {
        LocalDateTime start = LocalDate.of(2017, 1, 1).atStartOfDay();
        LocalDateTime end = LocalDate.of(2019, 1, 1).atStartOfDay();

        long count = Arrays.stream(RandomUtil.generateTimes(start, end, 1000)).filter(d -> !d.isBefore(start) && d.isBefore(end)).count();
        Assert.assertEquals(1000, count);

    }

    @Test
    public void generateDates() {
        LocalDate start = LocalDate.of(2017, 1, 1);
        LocalDate end = LocalDate.of(2019, 1, 1);

        long count = Arrays.stream(RandomUtil.generateDates(start, end, 1000)).filter(d -> !d.isBefore(start) && d.isBefore(end)).count();
        Assert.assertEquals(1000, count);
    }
}