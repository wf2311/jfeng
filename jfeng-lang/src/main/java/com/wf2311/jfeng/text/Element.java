package com.wf2311.jfeng.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @time 2017/06/27 15:57.
 */
public class Element {
    private List<Element> children;
    private String text;
    private Element parent;
    private Integer left;
    private Integer right;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void addElement(Element e) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(e);
    }

}
