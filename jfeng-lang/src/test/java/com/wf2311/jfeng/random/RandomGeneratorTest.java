package com.wf2311.jfeng.random;

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
        RateRandomGenerator generator = RateRandomGenerator.create()
                .addCondition(Condition.with(2, 2,2.5))
                .addCondition(Condition.with(2, 3,3.5))
                .addCondition(Condition.with(2, 4));
        List<Integer> list = IntStream.rangeClosed(1, 1000000).mapToObj(i -> generator.generateInt()).collect(toList());
        Map<Integer, List<Integer>> map = CollectionUtils.groupByFeature(list, i -> i);
        map.forEach((key, value) -> System.out.println(key + "\t" + value.size()));
    }
}