package com.wf2311.jfeng.lang;


import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author wf2311
 */
public abstract class TreeNode<N, T> {
    static final int SORT_ASC = 1;

    static final int SORT_DESC = -1;

    /**
     * 当前树的排序方式 1:按<code>sequence</code>从小到大;-1:按<code>sequence</code>从大到小
     */
    private int order = 0;

    /**
     * 主键
     */
    public abstract T getId();

    /**
     * 父级主键
     */
    public abstract T getParentId();

    /**
     * 序号
     */
    public abstract List<N> getChildren();

    /**
     * 设置直属子节点集合
     */
    public abstract void setChildren(List<N> children);

    /**
     * 直属子节点集合
     */
    public abstract Integer getSequence();

    /**
     * 新增节点
     */
    public void addNode(N node) {
        initChildren();
        this.getChildren().add(node);
    }

    /**
     * 批量新增节点
     */
    public void addNodes(List<N> nodes) {
        initChildren();
        this.getChildren().addAll(nodes);
    }

    public void initChildren() {
        if (this.getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
    }

    /**
     * 当前树(节点)的所有直属祖先节点集合
     */
//    @SuppressWarnings("unchecked")
//    public List<N> parentList() {
//        List<N> parentList = new ArrayList<>();
//        TreeNode<N, S> parentNode = (TreeNode<N, S>) this.parentNode;
//        if (parentNode == null) {
//            return parentList;
//        }
//        parentList.add((N) parentNode);
//        parentList.addAll(parentNode.parentList());
//        return parentList;
//    }

    /**
     * 当前树(节点)的所有子孙节点集合
     */
    @SuppressWarnings("unchecked")
    public List<N> childrenList() {
        List<N> childrenList = new ArrayList<>();
        if (this.isLeaf()) {
            return childrenList;
        }
        this.getChildren().forEach(node -> {
            childrenList.add(node);
            childrenList.addAll(((TreeNode<N, T>) node).childrenList());
        });
        return childrenList;
    }

    /**
     * 是否是叶子节点
     */
    public boolean isLeaf() {
        return this.getChildren() == null || this.getChildren().isEmpty();
    }

    /**
     * 找到当前树(节点)的某个子节点
     */
    @SuppressWarnings("unchecked")
    public TreeNode<N, T> childTree(T s) {
        if (s.equals(this.getId())) {
            return this;
        }
        if (this.isLeaf()) {
            return null;
        }
        return (TreeNode<N, T>) this.getChildren().stream()
                .filter(node -> ((TreeNode<N, T>) node).childTree(s) != null)
                .findFirst().orElse(null);
    }

    public TreeNode<N, T> asc() {
        sort(SORT_ASC);
        return this;
    }

    public TreeNode<N, T> desc() {
        sort(SORT_DESC);
        return this;
    }

    @SuppressWarnings("unchecked")
    protected void sort(int order) {
        if (order == this.order) {
            return;
        }

        if (order != SORT_DESC && order != SORT_ASC) {
            return;
        }
        if (isLeaf()) {
            return;
        }
        this.setChildren(
                this.getChildren().stream().sorted((n1, n2) -> TreeBuilder.compare(((TreeNode<N, T>) n1).getSequence(), ((TreeNode<N, T>) n2).getSequence(), order))
                        .collect(toList())
        );
        this.getChildren().forEach(node -> ((TreeNode<N, T>) node).sort(order));
        this.order = order;
    }
}
