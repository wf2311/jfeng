package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * 树(林)构造器
 *
 * @author wf2311
 */
public final class TreeBuilder<N, T> {

    private List<TreeNode<N, T>> list;
    private List<N> rootNodes;
    private Predicate<N> rootCondition;
    private int order = 0;


    @SuppressWarnings("unchecked")
    private TreeBuilder(List<N> list) {
        this.list = new ArrayList<>(list.size());
        for (N node : list) {
            this.list.add((TreeNode<N, T>) node);
        }
    }

    /**
     * 执行构造
     */
    @SuppressWarnings("unchecked")
    private void build0() {
        this.rootNodes = new ArrayList<>();
        for (TreeNode<N, T> node : this.list) {
            if (isRoot(node)) {
                this.rootNodes.add((N) node);
            }
            for (TreeNode<N, T> n : this.list) {
                if (node.getId().equals(n.getParentId())) {
                    if (node.getChildren() == null) {
                        List<N> cs = new ArrayList<>();
                        cs.add((N) n);
                        node.setChildren(cs);
                    } else {
                        node.getChildren().add((N) n);
                    }
                }
            }
        }
    }

    /**
     * 构造树(林)
     */
    public List<N> build() {
        build0();
        if (this.order == TreeNode.SORT_DESC || order == TreeNode.SORT_ASC) {
            sort();
        }
        return this.rootNodes;
    }

    @SuppressWarnings("unchecked")
    private void sort() {
        if (this.rootNodes == null || this.rootNodes.isEmpty()) {
            return;
        }
        this.rootNodes = this.rootNodes.stream().sorted((n1, n2) -> compare(((TreeNode<N, T>) n1).getSequence(), ((TreeNode<N, T>) n2).getSequence(), this.order))
                .collect(toList());
        this.rootNodes.forEach(node -> ((TreeNode<N, T>) node).sort(order));
    }

    /**
     * 到达数根部的判断条件，如果不指定，则默认以下条件:
     * <code>
     * type == null || type instanceof Number && ((Number) type).intValue() == 0;
     * </code>
     *
     * @param rootCondition 数根部的判断条件
     */
    public TreeBuilder<N, T> rootCondition(Predicate<N> rootCondition) {
        this.rootCondition = rootCondition;
        return this;
    }


    /**
     * 判断节点是否是根节点
     */
    @SuppressWarnings("unchecked")
    private boolean isRoot(TreeNode<N, T> node) {
        if (this.rootCondition != null) {
            return this.rootCondition.test((N) node);
        }
        T type = node.getParentId();
        return type == null || type instanceof Number && ((Number) type).intValue() == 0;
    }

    /**
     * 从树节点列表创建树构造器
     *
     * @param list 树节点列表
     * @param <N>  树节点类型
     * @param <T>  树节点主键类型
     */
    public static <N, T> TreeBuilder<N, T> of(List<N> list) {
        assertIsTree(list);
        return new TreeBuilder<>(list);
    }

    public TreeBuilder<N, T> asc() {
        this.order = TreeNode.SORT_ASC;
        return this;
    }

    public TreeBuilder<N, T> desc() {
        this.order = TreeNode.SORT_DESC;
        return this;
    }

    /**
     * 确保列表能组成树(林)
     */
    private static <N> void assertIsTree(List<N> list) {
        assertNoRepeatId(list);
        assertNotExistCircle(list);
    }

    /**
     * TODO 确保树节点不会构成环路
     */
    private static <N, T> void assertNotExistCircle(List<N> list) {

    }

    /**
     * 确保没有重复的树节点
     */
    @SuppressWarnings("unchecked")
    private static <N, T> void assertNoRepeatId(List<N> list) {
        long count = list.stream().map(n -> {
            TreeNode<N, T> node = (TreeNode<N, T>) n;
            return node.getId();
        }).distinct().count();
        if (count < list.size()) {
            throw new IllegalArgumentException("存在重复的树节点");
        }
    }

    /**
     * 比较两个<code>Integer</code>，规则如下：
     * <p>
     * 当order=1时，<code>null</code>总小于其他值；
     * 当order=-1时，<code>null</code>总小于其他值；
     * </p>
     * <pre>
     * Assert.assertEquals(-1, TreeBuilder.compare(1, 2, 1));
     * Assert.assertEquals(1, TreeBuilder.compare(1, 2, -1));
     * Assert.assertEquals(-1, TreeBuilder.compare(1, null, -1));
     * Assert.assertEquals(-1, TreeBuilder.compare(1, null, 1));
     * Assert.assertEquals(0, TreeBuilder.compare(null, null, -1));
     * Assert.assertEquals(0, TreeBuilder.compare(null, null, 1));
     * Assert.assertEquals(1, TreeBuilder.compare(null, 1, -1));
     * Assert.assertEquals(1, TreeBuilder.compare(null, 1, 1));
     * </pre>
     *
     * @param order
     * @return
     */
    public static int compare(Integer a, Integer b, int order) {
        if (order != TreeNode.SORT_DESC && order != TreeNode.SORT_ASC) {
            return 0;
        }
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return 1;
        }
        if (b == null) {
            return -1;
        }
        return Integer.compare(a, b) * order;
    }

}