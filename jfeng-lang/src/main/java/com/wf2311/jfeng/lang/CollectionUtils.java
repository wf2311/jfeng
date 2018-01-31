package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

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
        final int size = list.size();
        for (int i = 0; i < size; i += subSize) {
            parts.add(new ArrayList<>(list.subList(i, Math.min(size, i + subSize)))
            );
        }
        return parts;
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
    public static <K, T> Map<K, List<T>> getMapGroupByFeature(List<T> list, Function<T, K> groupFeature) {
        return getMapGroupByFeature(list, groupFeature, t -> t);
    }

    /**
     * 将列表按指定的特征条件分组，并用map存放
     */
    public static <K, V, T> Map<K, List<V>> getMapGroupByFeature(List<T> list, Function<T, K> groupFeature, Function<T, V> mappingToList) {
        Map<K, List<V>> map = list.stream()
                //过滤掉为空的情况
                .filter(StreamUtils.notNullResultFunction(groupFeature))
                .collect(groupingBy(groupFeature, mapping(mappingToList, toList())));
        List<T> nullGroup = list.stream().filter(StreamUtils.nullResultFunction(groupFeature)).collect(toList());
        //如果存在特征条件为的数据，加入map
        if (isNotEmpty(nullGroup)) {
            map.put(null, nullGroup.stream().map(mappingToList).collect(toList()));
        }
        return map;
    }

    /**
     * 得到列表的指定分组特征列表
     *
     * @param list         列表
     * @param groupFeature 分组特征
     * @return
     */
    public static <K, T> List<K> getGroupFeatureList(List<T> list, Function<T, K> groupFeature) {
        List<K> ks = list.stream().filter(StreamUtils.notNullResultFunction(groupFeature))
                .map(groupFeature).distinct().sorted().collect(toList());
        boolean hasNullFeature = list.stream().anyMatch(StreamUtils.nullResultFunction(groupFeature));
        if (hasNullFeature){
            ks.add(null);
        }
        return ks;
    }

    /**
     * 得到列表的指定分组特征列表
     *
     * @param list         列表
     * @param groupFeature 分组特征
     * @param comparator   排序依据
     * @return
     */
    public static <K, T> List<K> getGroupFeatureList(List<T> list, Function<T, K> groupFeature, Comparator<K> comparator) {
        Stream<K> stream = list.stream().filter(StreamUtils.notNullResultFunction(groupFeature))
                .map(groupFeature).distinct();
        if (comparator != null) {
            stream = stream.sorted(comparator);
        }
        List<K> ks = stream.collect(toList());
        boolean hasNullFeature = list.stream().anyMatch(StreamUtils.nullResultFunction(groupFeature));
        if (hasNullFeature){
            ks.add(null);
        }
        return ks;
    }

    /**
     * 得到列表中满足指定特征条件的子列表
     */
    public static <K, T> List<T> getSubListByGroupFeature(List<T> list, Function<T, K> groupFeature, K k) {
        Map<K, List<T>> map = getMapGroupByFeature(list, groupFeature);
        return map.get(k);
    }

    /**
     * 按照特征条件排序，得到列表中满足指定条件的第一个子列表
     */
    public static <K, T> List<T> getFirstSubListByGroupFeature(List<T> list, Function<T, K> groupFeature, boolean asc) {
        List<K> keys = getGroupFeatureList(list, groupFeature);
        K k;
        if (asc) {
            k = keys.get(0);
        } else {
            k = keys.get(keys.size() - 1);
        }
        return getSubListByGroupFeature(list, groupFeature, k);
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
