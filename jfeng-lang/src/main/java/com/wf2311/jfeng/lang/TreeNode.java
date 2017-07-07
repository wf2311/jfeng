package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wf2311
 */
public abstract class TreeNode<N, S> {
    /**
     * 主键
     */
    private S id;
    /**
     * 父级主键
     */
    private S parentId;

    /**
     * 父级节点
     */
    private N parentNode;
    /**
     * 直属子节点集合
     */
    private List<N> children;

    public S getId() {
        return id;
    }

    public void setParentId(S parentId) {
        this.parentId = parentId;
    }

    public S getParentId() {
        return parentId;
    }



    /**
     * 为避免在Json转换时产生循环，<code>parentNode</code>的getter方法省略get
     */
    public N parentNode() {
        return parentNode;
    }

    public void parentNode(N parentNode) {
        this.parentNode = parentNode;
    }

    public List<N> getChildren() {
        return children;
    }

    public void setChildren(List<N> children) {
        this.children = children;
    }

    /**
     * 新增节点
     */
    public void addNode(N node) {
        initChildren();
        children.add(node);
    }

    /**
     * 批量新增节点
     */
    public void addNodes(List<N> nodes) {
        initChildren();
        children.addAll(nodes);
    }

    public void initChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
    }

    /**
     * 当前树(节点)的所有直属祖先节点集合
     */
    @SuppressWarnings("unchecked")
    public List<N> parentList() {
        List<N> parentList = new ArrayList<>();
        TreeNode<N, S> parentNode = (TreeNode<N, S>) this.parentNode();
        if (parentNode == null) {
            return parentList;
        }
        parentList.add((N) parentNode);
        parentList.addAll(parentNode.parentList());
        return parentList;
    }

    /**
     * 当前树(节点)的所有子孙节点集合
     */
    @SuppressWarnings("unchecked")
    public List<N> childrenList() {
        List<N> childrenList = new ArrayList<>();
        if (this.isLeaf()) {
            return childrenList;
        }
        children.forEach(node -> {
            childrenList.add(node);
            childrenList.addAll(((TreeNode<N, S>) node).childrenList());
        });
        return childrenList;
    }

    /**
     * 是否是叶子节点
     */
    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    /**
     * 找到当前树(节点)的某个子节点
     */
    @SuppressWarnings("unchecked")
    public TreeNode<N, S> childTree(S s) {
        if (s.equals(id)) {
            return this;
        }
        if (isLeaf()) {
            return null;
        }
        return (TreeNode<N, S>) children.stream()
                .filter(node -> ((TreeNode<N, S>) node).childTree(s) != null)
                .findFirst().orElse(null);
    }
}
