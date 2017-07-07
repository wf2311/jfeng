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
     * 上级主键
     */
    private S parentId;
    /**
     * 子节点
     */
    private List<N> children;

    public S getId() {
        return id;
    }

    public S getParentId() {
        return parentId;
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
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(node);
    }


    public TreeNode<N, S> subTree(S s) {
        if (s.equals(id)) {
            return this;
        }
        //TODO 寻找子节点
        if (children == null || children.isEmpty()) {
            return null;
        }
        return null;
    }
}
