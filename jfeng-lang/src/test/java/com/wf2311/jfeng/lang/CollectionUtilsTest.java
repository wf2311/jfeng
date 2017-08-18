package com.wf2311.jfeng.lang;

import com.wf2311.jfeng.lang.random.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author wangfeng
 * @time 2017/04/18 19:35.
 */
public class CollectionUtilsTest {
    @Test
    public void test_splitList() throws Exception {
        int size = 10010;
        List<Integer> collect = IntStream.range(0, size)
                .boxed()
                .collect(toList());
        int per = 12;
        List<List<Integer>> lists = CollectionUtils.splitList(collect, per);
        Assert.assertEquals(size / per + (size % per > 0 ? 1 : 0), lists.size());
        Assert.assertEquals(per, lists.get(0).size());
        Assert.assertEquals(size % per == 0 ? per : size % per, lists.get(lists.size() - 1).size());
    }


    @Test
    public void test_distinctByKey() {
        String[] data = new String[]{
                "1,2,a,4,5,6",
                "2,2,b,4,5,6",
                "3,2,a,4,5,6",
                "4,2,d,4,5,6",
                "5,2,b,4,5,6",
                "6,2,f,4,5,6",
                "7,2,c,4,5,6",
                "8,2,g,4,5,6",
                "9,2,d,4,5,6",
                "10,2,g,4,5,6",
        };
        int[] arrays = Stream.of(data).map(d -> d.split(","))
                .filter(CollectionUtils.distinctByKey(array -> array[2]))
                .mapToInt(array -> Integer.valueOf(array[0]))
                .toArray();
        Assert.assertArrayEquals(new int[]{1, 2, 4, 6, 7, 8}, arrays);
    }

    @Test
    public void test2_distinctByKey() {
        List<List<A>> collect = IntStream.range(0, 100).mapToObj(i -> IntStream.range(0, 10000)
                .mapToObj(j -> new A(RandomUtil.getInt(i, 200), i)).collect(toList())
        ).collect(toList());
        collect.forEach(list -> System.out.println(list.get(0).getValue() + "\t:\t" + list.stream()
                .filter(CollectionUtils.distinctByKey(A::getKey)).count()));

    }

    @Test
    public void testNull(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = null;
        Assert.assertEquals(true,CollectionUtils.isEmpty(list));
        Assert.assertEquals(true,CollectionUtils.isEmpty(list2));
    }

    class A {
        private int key;
        private int value;

        A(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int getKey() {
            return key;
        }

        int getValue() {
            return value;
        }
    }

}