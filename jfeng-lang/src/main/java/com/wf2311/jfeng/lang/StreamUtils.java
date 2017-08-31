package com.wf2311.jfeng.lang;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author wf2311
 */
public final class StreamUtils {
    private StreamUtils() {
    }


    /**
     * 转换结果非空函数谓语
     *
     * @param function 转换函数
     * @param <K>      转换后的类型
     * @param <T>      转换前的类型
     */
    public static <K, T> Predicate<T> notNullResultFunction(Function<T, K> function) {
        return t -> !Objects.isNull(function.apply(t));
    }

    /**
     * 转换结果为空函数谓语
     *
     * @param function 转换函数
     * @param <K>      转换后的类型
     * @param <T>      转换前的类型
     */
    public static <K, T> Predicate<T> nullResultFunction(Function<T, K> function) {
        return t -> Objects.isNull(function.apply(t));
    }

    /**
     * 根据转换结果过滤重复的元素谓语
     * <pre>
     *        list.stream().filter(distinctByFunction(l -> l.getKey())).collect(toList());
     * </pre>
     */
    public static <T> Predicate<T> distinctByFunction(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
