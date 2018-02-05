package com.wf2311.jfeng.lang.random;

import com.wf2311.jfeng.utils.CollectionUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


/**
 * @author wangfeng
 * @time 2018/02/05 22:19.
 */
public class RandomGeneratorTest {


    @Test
    public void addCondition() {
        RandomGenerator generator = RandomGenerator.create().addCondition(Condition.with(3, 2)).addCondition(Condition.with(2, 4));
        List<Integer> list = IntStream.rangeClosed(1, 1000000).mapToObj(i -> generator.getInt()).collect(toList());
        Map<Integer, List<Integer>> map = CollectionUtils.groupByFeature(list, i -> i);
        map.forEach((key, value) -> System.out.println(key + "\t" + value.size()));
    }

    @Test
    public void addConditions() {
    }

    @Test
    public void setConditions() {
    }

    @Test
    public void getDouble() {
    }

    @Test
    public void getInt() {
    }

    @Test
    public void getLong() {
    }

    @Test
    public void getString() {
    }
}