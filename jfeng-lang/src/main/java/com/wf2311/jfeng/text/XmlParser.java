//package com.wf2311.jfeng.text;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wangfeng
// * @time 2017/06/28 18:59.
// */
//public class XmlParser {
//    private String left;
//    private String right;
//    private String text;
//    private List<Element> roots = new ArrayList<>();
//    private List<IndexRange> leftIndex = new ArrayList<>();
//    private List<IndexRange> rightIndex = new ArrayList<>();
//
//    public XmlParser(String text, String left, String right) {
//        this.left = left;
//        this.right = right;
//        this.text = text;
//        resolve();
//    }
//
//    public void resolve() {
//        String tmp;
//        int start = 0;
//        while (true) {
//            if (start == text.length()) {
//                break;
//            }
//            tmp = text.substring(start);
//            int a = tmp.indexOf(left);
//            int b = tmp.indexOf(right);
//
//            if (a < b && a >= 0) {
//                a += start;
//                leftIndex.add(a);
//                start = a + left.length();
//            } else if (a > b || a < 0) {
//                b += start;
//                rightIndex.add(b);
//                start = b + right.length();
//            } else {
//                break;
//            }
//        }
//        if (leftIndex.size() != rightIndex.size()) {
//            throw new IllegalArgumentException();
//        }
//        parse(leftIndex, rightIndex, null);
//    }
//
//    private void parse(List<Integer> leftIndex, List<Integer> rightIndex, Element parent) {
//        int size = leftIndex.size();
//        if (size == 0) {
//            return;
//        }
//        if (size == 1) {
//            Element e = new Element();
//            e.setLeft(leftIndex.get(0));
//            e.setRight(rightIndex.get(0));
//            if (parent != null) {
//                parent.addElement(e);
//                e.setParent(parent);
//            } else {
//                roots.add(e);
//            }
//            return;
//        }
//        int pos = size - 1;
//        boolean division = true;
//        while (leftIndex.get(pos) < rightIndex.get(pos)) {
//            if (pos == 0) {
//                pos = size - 1;
//                division = false;
//                break;
//            }
//            if (leftIndex.get(pos) > rightIndex.get(pos - 1)) {
//                break;
//            }
//            pos--;
//        }
//        if (division) {
//            parse(leftIndex.subList(0, pos), rightIndex.subList(0, pos), parent);
//            parse(leftIndex.subList(pos, size), rightIndex.subList(pos, size), parent);
//        } else {
//            Element e = new Element();
//            e.setLeft(leftIndex.get(0));
//            e.setRight(rightIndex.get(pos));
//            if (parent != null) {
//                parent.addElement(e);
//                e.setParent(parent);
//            } else {
//                roots.add(e);
//            }
//            parse(leftIndex.subList(1, pos + 1), rightIndex.subList(0, pos), e);
//        }
//    }
//}
