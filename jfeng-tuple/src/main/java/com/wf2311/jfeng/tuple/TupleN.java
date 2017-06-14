package com.wf2311.jfeng.tuple;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * 表示有N个元素的元组类型
 * 可迭代
 * 不可变，线程安全
 *
 * @author peiyu
 */
public final class TupleN extends Tuple {

    private TupleN(final Object... elements) {
        super(elements);
    }

    /**
     * 反转元组
     *
     * @return 反转后的元组
     */
    @Override
    public TupleN reverse() {
        final Object[] array = new Object[this.size()];
        this.forEachWithIndex((index, obj) -> array[array.length - 1 - index] = obj);
        return TupleN.with(array);
    }

    /**
     * 从一个数组生成一个元组
     *
     * @param elements 数组
     * @return 元组
     */
    public static TupleN with(final Object... elements) {
        requireNonNull(elements, "elements is null");
        return new TupleN(elements);
    }

    /**
     * 从一个列表生成一个元组,元组大小等于列表大小
     *
     * @param list 列表
     * @return 元组
     */
    public static TupleN withList(final List<Object> list) {
        requireNonNull(list, "list is null");
        return TupleN.with(list.toArray());
    }
}
