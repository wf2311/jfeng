package com.wf2311.jfeng.random;

import org.junit.Test;

import java.util.*;

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

        for (int i = 1; i <= 2003; i++) {
            list.add(new Person(i, generator.generateInt()));
        }


        List<Queue<Person>> queues = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            queues.add(new LinkedList<>());
        }
        for (Person p : list) {
            queues.get(p.getType() - 1).add(p);
        }

        List<List<Person>> result = new ArrayList<>();

        boolean finish = false;
        int count = 1;
        while (!finish) {

            List<Person> team = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                count++;
                if (queues.get(i).size() > 0) {
                    team.add(queues.get(i).poll());
                } else {
                    int index = findIndexOfMost(queues);
                    if (index < 0) {
                        finish = true;
                        break;
                    } else {
                        team.add(queues.get(index).poll());
                    }
                }

            }
            if (team.size() > 0) {
                result.add(team);
            }
        }

        result.forEach(i -> System.out.println(i));
        System.out.println(count);

    }

    private int findIndexOfMost(List<Queue<Person>> queues) {
        int index = 0;
        int max = 0;
        for (int i = 0; i < queues.size(); i++) {
            int size = queues.get(i).size();

            if (size > max) {
                index = i;
                max = size;
            }
        }
        return max == 0 ? -1 : index;
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

        @Override
        public String toString() {
            return id + "=" + type;
        }
    }
}
