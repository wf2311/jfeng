package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

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
}
