package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * @author wf2311
 */
public final class CollectionUtils {
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
     * 判断集合不为空
     */
    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断集合为空
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return !isNotEmpty(collection);
    }

    /**
     * 将列表按指定的特征分组，并用map存放
     */
    public static <K, V, T> Map<K, List<V>> getMapGroupByFeature(List<T> list, Function<T, K> groupFeature, Function<T, V> mappingToList) {
        return list.stream()
                .collect(groupingBy(groupFeature, mapping(mappingToList, toList())));
    }

}
