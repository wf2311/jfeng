package com.wf2311.jfeng.text;

import java.util.List;

/**
 * @author admin
 * @time 2017/06/27 15:57.
 */
public class Element {
    private List<Element> children;
    private String text;
    private Element parent;

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }
}
