package com.wf2311.jfeng.lang.random;


import com.google.common.util.concurrent.AtomicDouble;
import com.wf2311.jfeng.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.function.Function.identity;

/**
 * 随机数生成器
 *
 * @author wangfeng
 */
public class RandomGenerator {

    private List<Condition> conditions;
    private double[] keys;
    private Map<Double, Condition> map;
    private Double maxRate;
    private Random random = new Random();
    private boolean updated = false;

    public static RandomGenerator create(){
        return new RandomGenerator();
    }

    /**
     * 新增条件
     *
     * @param condition
     * @return
     */
    public RandomGenerator addCondition(Condition condition) {
        ensureConditionsIsNotNull();
        conditions.add(condition);
        updated = true;
        return this;
    }

    /**
     * 批量新增条件
     *
     * @param conditions
     * @return
     */
    public RandomGenerator addConditions(List<Condition> conditions) {
        ensureConditionsIsNotNull();
        this.conditions.addAll(conditions);
        updated = true;
        return this;
    }

    /**
     * 设置条件
     *
     * @param conditions
     * @return
     */
    public RandomGenerator setConditions(List<Condition> conditions) {
        this.conditions = conditions;
        updated = true;
        return this;
    }

    private void ensureConditionsIsNotNull() {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
    }

    private void init() {
        if (CollectionUtils.isEmpty(conditions)) {
            throw new IllegalStateException("有效条件为空！");
        }
        if (updated) {
            map = toMap(conditions);
            keys = map.keySet().stream().mapToDouble(key -> key).sorted().toArray();
            maxRate = keys[keys.length - 1];
            updated = false;
        }
    }

    private double get0() {
        init();
        double section0 = random(0, maxRate);
        Condition condition = getSection(section0);
        return random(condition.start, condition.end);
    }

    public double getDouble() {
        return get0();
    }

    public int getInt() {
        return (int) get0();
    }

    public long getLong() {
        return (long) get0();
    }

    public String getString() {
        init();
        double section0 = random(0, maxRate);
        Condition condition = getSection(section0);
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
        double k = DoubleStream.of(keys).filter(key -> key >= d).findFirst().orElseThrow(IllegalArgumentException::new);
        return map.get(k);
    }

    private double random(double start, double end) {
        if (start >= end) {
            return start;
        }
        return random.doubles(1, start, end).toArray()[0];
    }

}
