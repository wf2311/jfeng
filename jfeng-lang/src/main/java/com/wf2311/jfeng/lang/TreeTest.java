package com.wf2311.jfeng.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfeng
 * @time 2017/07/07 16:43.
 */
public class TreeTest extends TreeNode<TreeTest, Integer> {
    private Integer id;

    private String name;

    private Integer parentId;

    private List<TreeTest> children;

    public TreeTest(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<TreeTest> getChildren() {
        return children;
    }

    public void setChildren(List<TreeTest> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "Tree [id=" + id + ", name=" + name + ", parentId=" + parentId
                + ", children=" + children + "]";
    }

    public static void main(String[] args) {
        TreeTest tree5 = new TreeTest(5, "二级节点5", 2);
        TreeTest tree3 = new TreeTest(3, "顶层节点3", 0);
        TreeTest tree8 = new TreeTest(8, "三级节点8", 4);

        TreeTest tree4 = new TreeTest(4, "二级节点4", 1);
        TreeTest tree6 = new TreeTest(6, "二级节点6", 3);
        TreeTest tree9 = new TreeTest(9, "三级节点9", 5);
        TreeTest tree2 = new TreeTest(2, "顶层节点2", 0);

        TreeTest tree7 = new TreeTest(7, "三级节点7", 4);
        TreeTest tree1 = new TreeTest(1, "顶层节点1", 0);

        List<TreeTest> trees = new ArrayList<>();
        trees.add(tree9);
        trees.add(tree8);
        trees.add(tree7);
        trees.add(tree6);
        trees.add(tree5);
        trees.add(tree4);
        trees.add(tree3);
        trees.add(tree2);
        trees.add(tree1);

        List<TreeTest> rootTrees = TreeBuilder.of(trees)
                .rootCondition(test -> test.getParentId() == 0).build();

        for (TreeTest tree : rootTrees) {
            System.out.println(tree.toString());
        }

    }

}