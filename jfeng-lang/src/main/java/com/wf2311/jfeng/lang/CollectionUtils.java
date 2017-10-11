package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @author wangfeng
 * @time 2017/04/18 19:32.
 */
public class CollectionUtils {
    private CollectionUtils() {
    }

    /**
     * 将List按指定大小拆分
     *
     * @param list
     * @param subSize 每个子List的大小
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, final int subSize) {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += subSize) {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(N, i + subSize)))
            );
        }
        return parts;
    }

    /**
     * 根据属性过滤重复的元素
     * <pre>
     *        list.stream().filter(distinctByKey(l -> l.getKey())).collect(toList());
     * </pre>
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * List类型转换
     * <pre>
     *     List<Integer> integerList = convertList(Arrays.asList("1","2","3"), s -> Integer.parseInt(s));
     * </pre>
     *
     * @param source 源list
     * @param func   转换函数
     * @param <T>    源list类型
     * @param <U>    目标list类型
     */
    public static <T, U> List<U> convertList(List<T> source, Function<T, U> func) {
        return source.stream().map(func).collect(toList());
    }

    /**
     * List转换为数组
     * <pre>
     *   Double[] doubleArr = convertArray(Arrays.asList("1","2","3"), Double::parseDouble, Double[]::new);
     * </pre>
     *
     * @param source    源list
     * @param func      转换函数
     * @param generator 函数：产生一个数组
     * @param <T>       源list类型
     * @param <U>       目标list类型
     * @return
     */
    public static <T, U> U[] convertArray(T[] source, Function<T, U> func, IntFunction<U[]> generator) {
        return Arrays.stream(source).map(func).toArray(generator);
    }

}
