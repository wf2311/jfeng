package com.wf2311.jfeng.lang;

import com.alibaba.fastjson.JSON;
import com.wf2311.jfeng.lang.random.RandomUtil;
import com.wf2311.jfeng.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
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

    protected static final boolean MAN = true;
    protected static final boolean WOMAN = true;

    @Data
    @AllArgsConstructor
    class User {
        int id;
        String name;
        boolean sex;
        Integer age;

    }

    private List<User> getUsers() {
        int i = 1;
        List<User> users = new ArrayList<>();
        users.add(new User(i++, "aa", MAN, 18));
        users.add(new User(i++, "ab", WOMAN, 19));
        users.add(new User(i++, "ac", MAN, 20));
        users.add(new User(i++, "ad", MAN, 18));
        users.add(new User(i++, "ae", WOMAN, 22));
        users.add(new User(i++, "af", MAN, 21));
        users.add(new User(i++, "ba", WOMAN, 17));
        users.add(new User(i++, "bb", MAN, 19));
        users.add(new User(i++, "bc", MAN, 22));
        users.add(new User(i++, "bd", WOMAN, 23));
        users.add(new User(i++, "ca", MAN, 21));
        users.add(new User(i++, "cd", WOMAN, 20));
        users.add(new User(i++, "cd", MAN, 25));
        users.add(new User(i++, "da", MAN, 23));
        users.add(new User(i++, "dg", WOMAN, 16));
        users.add(new User(i++, "df", MAN, 19));
        users.add(new User(i++, "dq", MAN, 26));
        users.add(new User(i++, "ea", WOMAN, 29));
        users.add(new User(i++, "ed", WOMAN, null));
        return users;
    }

    @Test
    public void getMapGroupByFeature() throws Exception {
        Assert.assertEquals(Arrays.asList(16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 29, null), CollectionUtils.getGroupFeatureList(getUsers(), User::getAge, Integer::compareTo));
    }

    @Test
    public void getMapGroupByFeature2() throws Exception {
        List<Integer> list = CollectionUtils.getGroupFeatureList(getUsers(), User::getAge, (o1, o2) -> -Integer.compare(o1, o2));
        Assert.assertEquals(Arrays.asList(29, 26, 25, 23, 22, 21, 20, 19, 18, 17, 16, null), list);
    }

    @Test
    public void getMapGroupByFeature3() throws Exception {
        Map<Integer, List<User>> map = CollectionUtils.getMapGroupByFeature(getUsers(), User::getAge);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void test_getSubListByGroupFeature() {
        List<User> list = CollectionUtils.getFirstSubListByGroupFeature(getUsers(), User::getAge, true);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(new Integer(16), list.get(0).getAge());
        Assert.assertEquals("dg", list.get(0).getName());
    }

    @Test
    public void test_getSubListByGroupFeature2() {
        List<User> list = CollectionUtils.getSubListByGroupFeature(getUsers(), User::getAge, null);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(null, list.get(0).getAge());
        Assert.assertEquals("ed", list.get(0).getName());
    }

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
                .filter(StreamUtils.distinctByFunction(array -> array[2]))
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
                .filter(StreamUtils.distinctByFunction(A::getKey)).count()));

    }

    @Test
    public void testNull() {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = null;
        Assert.assertEquals(true, CollectionUtils.isEmpty(list));
        Assert.assertEquals(true, CollectionUtils.isEmpty(list2));
    }

    @Data
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

    @Test
    public void testList() {
        List<A> list = new ArrayList<>();
        list.add(new A(1, 1));
        list.add(new A(2, 1));
        list.add(new A(3, 1));
        list.add(new A(4, 1));
        list.add(new A(5, 1));
        list.add(new A(6, 1));


        List<A> l1 = new ArrayList<>(list);
        List<A> l2 = new ArrayList<>(list);
        l1.remove(1);
        System.out.println(list.size());
        System.out.println(l1.size());
        System.out.println(l2.size());

    }

}