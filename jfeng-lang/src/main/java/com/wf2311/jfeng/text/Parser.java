package com.wf2311.jfeng.text;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wf2311
 */
public class Parser {
    private String left;
    private String right;
    private String text;
    private List<Element> roots = new ArrayList<>();
    List<Integer> leftIndex = new ArrayList<>();
    List<Integer> rightIndex = new ArrayList<>();

    public Parser(String text, String left, String right) {
        this.left = left;
        this.right = right;
        this.text = text;
    }

    public List resolve() {
        String tmp;
        int start = 0;
        while (true) {
            if (start == text.length()) {
                break;
            }
            tmp = text.substring(start);
            int a = tmp.indexOf(left);
            int b = tmp.indexOf(right);

            if (a < b && a >= 0) {
                a += start;
                leftIndex.add(a);
                start = a + 1;
            } else if (a > b || a < 0) {
                b += start;
                rightIndex.add(b);
                start = b + 1;
            } else {
                break;
            }
        }
        if (leftIndex.size() != rightIndex.size()) {
            throw new IllegalArgumentException();
        }
        List<Integer[]> position = new ArrayList<>();
        calculate(leftIndex, rightIndex, position, null);
        return position;
    }

    private void calculate(List<Integer> leftIndex, List<Integer> rightIndex, List<Integer[]> position, Element parent) {
        int size = leftIndex.size();
        if (size == 0) {
            return;
        }
        if (size == 1) {
            Integer[] p = new Integer[2];
            p[0] = leftIndex.get(0);
            p[1] = rightIndex.get(0);
            Element e = new Element();
            e.setLeft(leftIndex.get(0));
            e.setRight(rightIndex.get(0));
            position.add(p);
            if (parent != null) {
                parent.addElement(e);
            } else {
                roots.add(e);
            }
            return;
        }
        int pos = size - 1;
        boolean division = true;
        while (leftIndex.get(pos) < rightIndex.get(pos)) {
            if (pos == 0) {
                pos = size - 1;
                division = false;
                break;
            }
            if (leftIndex.get(pos) > rightIndex.get(pos - 1)) {
                break;
            }
            pos--;
        }
        if (division) {
            calculate(leftIndex.subList(0, pos), rightIndex.subList(0, pos), position, parent);
            calculate(leftIndex.subList(pos, size), rightIndex.subList(pos, size), position, parent);
        } else {
            Integer[] p = new Integer[2];
            p[0] = leftIndex.get(0);
            p[1] = rightIndex.get(pos);
            position.add(p);
            Element e = new Element();
            e.setLeft(leftIndex.get(0));
            e.setRight(rightIndex.get(pos));
            position.add(p);
            if (parent != null) {
                parent.addElement(e);
            } else {
                roots.add(e);
            }
            calculate(leftIndex.subList(1, pos + 1), rightIndex.subList(0, pos), position, e);
        }
    }


    public static void main(String[] args) {

        String text1 = "((())())()(()())";

        Parser parser = new Parser(text1, "(", ")");
        List resolve = parser.resolve();
        for (int i = 0; i < resolve.size(); i++) {
            System.out.println(JSON.toJSONString(resolve.get(i)));
        }
        System.out.println(JSON.toJSON(parser.roots));

    }

}
