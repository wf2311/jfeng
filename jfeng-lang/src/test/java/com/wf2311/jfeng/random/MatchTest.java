package com.wf2311.jfeng.random;

import com.wf2311.jfeng.utils.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wf2311
 * @date 2018年04月03日
 */
public class MatchTest {


    @Test
    public void test1() {

        RateRandomGenerator generator = RateRandomGenerator.create().addConditions(
                Arrays.asList(
                        Condition.with(3, 1),
                        Condition.with(2, 2),
                        Condition.with(1, 3),
                        Condition.with(2.3, 4),
                        Condition.with(3.5, 5)
                ));


        List<Person> list = new ArrayList<>();

        for (int i = 1; i <= 2000; i++) {
            list.add(new Person(i, generator.generateInt()));
        }

        Map<Integer, List<Person>> map = CollectionUtils.getMapGroupByFeature(list, Person::getType);
        List<List<Person>> collect = map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        Integer min = collect.stream().map(List::size).sorted().findFirst().get();
        List<List<Person>> result = new ArrayList<>();


    }

    class Person {
        int id;
        int type;

        public Person(int id, int type) {
            this.id = id;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
