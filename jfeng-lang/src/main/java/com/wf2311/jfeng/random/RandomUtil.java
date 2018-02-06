package com.wf2311.jfeng.random;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.NANOS;

/**
 * @author wangfeng
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();
    private static final int STREAM_SIZE = 1;

    private RandomUtil() {
    }

    /**
     * 产生一个[start,end)的随机数
     */
    public static double generateDouble(double start, double end) {
        return generateDoubles(start, end, STREAM_SIZE)[0];
    }

    /**
     * 产生一个大小为size的所有元素取值范围在[start,end)的随机数数组
     */
    public static double[] generateDoubles(double start, double end, int size) {
        if (end < start) {
            throw new IllegalArgumentException("数值start必须大于等于end");
        }
        return RANDOM.doubles(size, start, end).toArray();
    }

    /**
     * 产生一个[start,end)的随机数
     */
    public static int generateInt(int start, int end) {
        return generateInts(start, end, STREAM_SIZE)[0];
    }

    /**
     * 产生一个大小为size的所有元素取值范围在[start,end)的随机数数组
     */
    public static int[] generateInts(int start, int end, int size) {
        if (end < start) {
            throw new IllegalArgumentException("数值start必须大于等于end");
        }
        return RANDOM.ints(size, start, end).toArray();
    }

    /**
     * 产生一个[start,end)的随机数
     */
    public static long generateLong(long start, long end) {
        return generateLongs(start, end, STREAM_SIZE)[0];
    }

    /**
     * 产生一个大小为size的所有元素取值范围在[start,end)的随机数数组
     */
    public static long[] generateLongs(long start, long end, int size) {
        if (end < start) {
            throw new IllegalArgumentException("数值start必须大于等于end");
        }
        return RANDOM.longs(STREAM_SIZE, start, end).toArray();
    }


    /**
     * 产生一个[start,end)的随机时间
     */
    public static LocalDateTime generateLocalDateTime(LocalDateTime start, LocalDateTime end) {
        return getLocalDateTimes(start, end, 1)[0];
    }

    /**
     * 产生一个大小为size的所有元素取值范围在[start,end)的随机时间数组
     */
    public static LocalDateTime[] getLocalDateTimes(LocalDateTime start, LocalDateTime end, int size) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("数值start必须晚于或等于end");
        }
        long millis = start.until(end, NANOS);
        return Arrays.stream(generateLongs(0, millis, size)).mapToObj(start::plusNanos).toArray(LocalDateTime[]::new);
    }

    /**
     * 产生一个[start,end)的随机日期
     */
    public static LocalDate generateLocalDate(LocalDate start, LocalDate end) {
        return generateLocalDates(start, end, 1)[0];
    }

    /**
     * 产生一个大小为size的所有元素取值范围在[start,end)的随机日期数组
     */
    public static LocalDate[] generateLocalDates(LocalDate start, LocalDate end, int size) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("数值start必须晚于或等于end");
        }
        int days = (int) start.until(end, DAYS);
        return Arrays.stream(generateInts(0, days, size)).mapToObj(start::plusDays).toArray(LocalDate[]::new);
    }

}
