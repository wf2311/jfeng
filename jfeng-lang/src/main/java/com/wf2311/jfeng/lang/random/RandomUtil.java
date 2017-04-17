package com.wf2311.jfeng.lang.random;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static java.time.temporal.ChronoUnit.NANOS;

/**
 * @author wangfeng
 * @time 2017/04/07 10:35.
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();
    private static final int STREAM_SIZE = 1;

    /**
     * 产生一个[start,end)的随机数
     */
    public static double getDouble(double start, double end) {
        if (end < start) {
            throw new RuntimeException("数值start必须大于等于end");
        }
        if (start == end) {
            return start;
        }
        return RANDOM.doubles(STREAM_SIZE, start, end).findFirst().getAsDouble();
    }

    /**
     * 产生一个[start,end)的随机数
     */
    public static int getInt(int start, int end) {
        if (end < start) {
            throw new RuntimeException("数值start必须大于等于end");
        }
        if (start == end) {
            return start;
        }
        return RANDOM.ints(STREAM_SIZE, start, end).findFirst().getAsInt();
    }

    /**
     * 产生一个[start,end)的随机数
     */
    public static long getLong(long start, long end) {
        if (end < start) {
            throw new RuntimeException("数值start必须大于等于end");
        }
        if (start == end) {
            return start;
        }
        return RANDOM.longs(STREAM_SIZE, start, end).findFirst().getAsLong();
    }


    /**
     * 产生一个[start,end)的随机时间
     */
    public static LocalDateTime getLocalDateTime(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new RuntimeException("数值start必须晚于或等于end");
        }
        if (start == end) {
            return start;
        }
        long millis = start.until(end, NANOS);
        return start.plus(getLong(0, millis), NANOS);
    }

    public static String getTimeLong() {
        String s = String.valueOf(System.nanoTime());
        synchronized (s) {

            s = s.substring(s.length() - 6);
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + s;//+ getInt(100000, 1000000);
    }

}
