package com.wf2311.jfeng.tuple;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * 表示有1个元素的元组类型
 * 可迭代
 * 不可变，线程安全
 *
 * @author peiyu
 */
public final class Tuple1<A> extends Tuple {

    public final A first;

    private Tuple1(final A first) {
        super(first);
        this.first = first;
    }

    /**
     * 创建一个包含1个元素的元组
     *
     * @param first 第一个元素
     * @param <A>   元素类型
     * @return 元组
     */
    public static <A> Tuple1<A> with(final A first) {
        return new Tuple1<>(first);
    }

    /**
     * 通过列表创建一个包含1个元素的元组
     * 包含的元素是列表的第一个元素，如果不存在，则为null
     *
     * @param list 列表
     * @param <A>  元素类型
     * @return 元组
     */
    public static <A> Tuple1<A> with(final List<? extends A> list) {
        requireNonNull(list, "list is null");
        if (list.size() == 0)
            return new Tuple1<>(null);
        return new Tuple1<>(list.get(0));
    }

    /**
     * 反转元组
     *
     * @return 反转后的元组
     */
    @Override
    public Tuple1<A> reverse() {
        return Tuple1.with(this.first);
    }
}
