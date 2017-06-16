package com.wf2311.jfeng.tuple;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.System.arraycopy;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

/**
 * 元组类型
 * 用于表示多个不同类型的数据集合
 * 可迭代
 * 不可变，线程安全
 *
 * @author peiyu
 * @see Tuple2
 */
public abstract class Tuple implements Iterable<Object>, Serializable {

    private final List<Object> elements;

    public Tuple(final Object... elements) {
        this.elements = Arrays.asList(elements);
    }

    /**
     * 将元组转换成列表
     *
     * @return 转换得到的列表
     */
    public final List<Object> toList() {
        return new ArrayList<>(this.elements);
    }

    /**
     * 将元组转换成数组
     *
     * @return 转换得到的数组
     */
    public final Object[] toArray() {
        return this.elements.toArray();
    }

    /**
     * 得到元组的大小
     *
     * @return 元组的大小
     */
    public final int size() {
        return this.elements.size();
    }

    /**
     * 获取元组中指定位置的元素
     *
     * @param index 元组中的位置
     * @return 对应元素
     */
    public final Object get(final int index) {
        return this.elements.get(index);
    }

    /**
     * 判断元组中是否包含某元素
     *
     * @param value 需要判定的元素
     * @return 是否包含
     */
    public final boolean contains(final Object value) {
        return this.elements.contains(value);
    }

    @Override
    public final Iterator<Object> iterator() {
        return this.elements.iterator();
    }

    @Override
    public final Spliterator<Object> spliterator() {
        return this.elements.spliterator();
    }

    /**
     * 将元组转成流
     *
     * @return 流
     */
    public final Stream<Object> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * 将元组转成并行流
     *
     * @return 流
     */
    public final Stream<Object> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    /**
     * 迭代元组
     *
     * @param action 迭代函数
     */
    @Override
    public final void forEach(final Consumer<? super Object> action) {
        requireNonNull(action, "action is null");
        this.elements.forEach(action);
    }

    /**
     * 带序号迭代元组
     *
     * @param action 带序号的迭代函数
     */
    public final void forEachWithIndex(final BiConsumer<Integer, ? super Object> action) {
        requireNonNull(action, "action is null");
        for (int i = 0, length = this.elements.size(); i < length; i++) {
            action.accept(i, this.elements.get(i));
        }
    }

    /**
     * 截取元组指定部分
     *
     * @param start 起始位置
     * @param end   终止位置
     * @return 截取得到的元组
     */
    public final Tuple subTuple(final int start, final int end) {
        if (start < 0 || end < 0)
            throw new IllegalArgumentException("start, end must >= 0");
        if (end >= this.elements.size())
            throw new IllegalArgumentException("this tuple's size is" + this.elements.size());
        int length = end - start + 1;
        if (length <= 0) {
            throw new IllegalArgumentException("end must >= start");
        }

        switch (length) {
            case 1:
                return Tuple1.with(this.elements.get(start));
            case 2:
                return Tuple2.with(this.elements.get(start), this.elements.get(end));
            case 3:
                return Tuple3.with(this.elements.get(start), this.elements.get(start + 1), this.elements.get(end));
            case 4:
                return Tuple4.with(this.elements.get(start), this.elements.get(start + 1), this.elements.get(start + 2), this.elements.get(end));
            default:
                return TupleN.with(this.elements.subList(start, end));
        }
    }

    /**
     * 于其他元组合并成新元组
     *
     * @param tuples 需要合并的元组
     * @return 合并后的新元组
     */
    public final Tuple add(final Tuple... tuples) {
        requireNonNull(tuples, "tuple is null");
        if (tuples.length == 0)
            return this;
        List<Object> temp = new ArrayList<>(this.elements);
        for (Tuple t : tuples) {
            temp.addAll(t.elements);
        }
        return TupleN.withList(temp);
    }

    /**
     * 将元组里的元素重复指定次数
     *
     * @param times 重复次数
     * @return 得到的元组
     */
    public final Tuple repeat(final int times) {
        if (times < 0)
            throw new IllegalArgumentException("times must >= 0");
        return TupleN.with(IntStream.range(0, times)
                .mapToObj(i -> this.elements.toArray())
                .reduce((a, b) -> {
                    Object[] temp = new Object[a.length + b.length];
                    arraycopy(a, 0, temp, 0, a.length);
                    arraycopy(b, 0, temp, a.length, b.length);
                    return temp;
                }).get());
    }

    /**
     * 比较2个元组是否相同
     * 元组里的所有位置上的元素都相同，即为元组相同
     *
     * @param obj 需要比较的元组
     * @return 比较结果
     */
    @Override
    public final boolean equals(final Object obj) {
        return !isNull(obj) && (obj == this || obj instanceof Tuple && ((Tuple) obj).elements.equals(this.elements));
    }

    @Override
    public final int hashCode() {
        return this.elements.hashCode();
    }

    /**
     * 得到元组的字符串，比如(123,456)
     *
     * @return 元组的字符串
     */
    @Override
    public final String toString() {
        return this.elements.stream()
                .map(Objects::toString)
                .collect(joining(", ", "(", ")"));
    }

    /**
     * 反转元组
     * 反转后元组大小不变，子类各自实现可以达到最好性能，也可以指定返回值类型，方便使用
     *
     * @return 反转后的元组
     */
    public abstract Tuple reverse();

    /**
     * 从一个列表生成一个元组
     *
     * @param list 列表
     * @return 元组
     */
    public static Tuple withList(final List<Object> list) {
        requireNonNull(list, "list is null");
        switch (list.size()) {
            case 0:
                return Tuple0.with();
            case 1:
                return Tuple1.with(list.get(0));
            case 2:
                return Tuple2.with(list.get(0), list.get(1));
            case 3:
                return Tuple3.with(list.get(0), list.get(1), list.get(2));
            case 4:
                return Tuple4.with(list.get(0), list.get(1), list.get(2), list.get(3));
            default:
                return TupleN.withList(list);
        }
    }

    public static Tuple with(final Object... elements) {
        return withList(Arrays.asList(elements));
    }
}
