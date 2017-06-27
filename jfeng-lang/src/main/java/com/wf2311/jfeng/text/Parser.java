package com.wf2311.jfeng.text;

import java.util.List;

/**
 * @author wf2311
 */
public class Parser {
    private String left;
    private String right;
    private String text;
    private List<Element> roots;

    public Parser(String text, String left, String right) {
        this.left = left;
        this.right = right;
        this.text = text;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Element> getRoots() {
        return roots;
    }

    public void setRoots(List<Element> roots) {
        this.roots = roots;
    }
}
