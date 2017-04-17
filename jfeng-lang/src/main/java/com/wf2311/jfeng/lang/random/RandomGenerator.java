package com.wf2311.jfeng.lang.random;


import com.google.common.util.concurrent.AtomicDouble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.function.Function.identity;

/**
 *
 * @author wangfeng
 * @time 2017/04/07 13:23.
 */
public class RandomGenerator {

    private List<Condition> conditions;
    private double[] keys;
    private Map<Double, Condition> map;
    private Double maxRate;
    private Random random = new Random();

    public RandomGenerator addCondition(Condition condition) {
        ensureConditionsIsNotNull();
        conditions.add(condition);
        return this;
    }

    public RandomGenerator addConditons(List<Condition> conditions) {
        ensureConditionsIsNotNull();
        this.conditions.addAll(conditions);
        return this;
    }

    public RandomGenerator setConditons(List<Condition> conditions) {
        this.conditions = conditions;
        return this;
    }

    private void ensureConditionsIsNotNull() {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
    }

    private void init() {
        if (conditions == null || conditions.size() == 0) {
            throw new RuntimeException("有效条件为空！");
        }

        map = toMap(conditions);
        keys = map.keySet().stream().mapToDouble(key -> key).sorted().toArray();
        maxRate = keys[keys.length - 1];
    }

    private double _get() {
        init();
        double _section = random(0, maxRate);
        Condition condition = getSection(_section);
        return random(condition.start, condition.end);
    }

    public double getDouble() {
        return _get();
    }

    public int getInt() {
        return (int) _get();
    }

    public long getLong(){
        return (long) _get();
    }

    public String getString(){
        init();
        double _section = random(0, maxRate);
        Condition condition = getSection(_section);
        return condition.value;
    }


    private Map<Double, Condition> toMap(List<Condition> list) {
        AtomicDouble sum = new AtomicDouble(0);
        return list.stream()
                .filter(condition -> condition.rate != 0)
                .collect(Collectors.toMap(curry(sum::addAndGet, condition -> condition.rate), identity()));
    }

    private <T, U, R> Function<T, R> curry(Function<U, R> target, Function<T, U> mapper) {
        return (it) -> target.apply(mapper.apply(it));
    }


    private Condition getSection(double d) {
        double k = DoubleStream.of(keys).filter(key -> key >= d).findFirst().getAsDouble();
        return map.get(k);
    }

    private double random(double start, double end) {
        if (start >= end) {
            return start;
        }
        double d= random.doubles(1, start, end).toArray()[0];
        return d;
    }

}
