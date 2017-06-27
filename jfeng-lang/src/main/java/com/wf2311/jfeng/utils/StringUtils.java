package com.wf2311.jfeng.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author wf2311
 */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isWrapWith(String text, String head, String foot) {
        return false;
    }

    /**
     * 用指定符号包裹文本
     *
     * @param text 文本
     * @param head 包裹符头
     * @param foot 包裹符尾
     * @return
     */
    public static String wrapWith(String text, String head, String foot) {

        return head + text + foot;
    }


    //=============================== copy from org.apache.commons.lang3.StringUtils ===================================
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    //==================================================================================================================

    public static List resolve(String text, String left, String right) {
        String tmp;
        int start = 0;
        List<Integer> leftIndex = new ArrayList<>();
        List<Integer> rightIndex = new ArrayList<>();
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
        Collections.reverse(rightIndex);
        List<Integer[]> position = new ArrayList<>();
        calculate(leftIndex, rightIndex, position);

        return position;
    }

    /**
     * 情况1：
     * <code>
     * 1、 2、 3、6、9、11
     * 12、10、8、7、5、4
     * <p>
     * 0 1 2 3 4 5 6 7 8 9 10 11
     * ( ( ( ) ) ( ) ) ( ) (  )
     * (0,7)、(1,4)、(2,3)、(5,6)、(8,9)、(10,11)
     * </code>
     * 情况2：
     * <code>
     * 1、 2、 3、6、9、10
     * 12、11、8、7、5、4
     * </code>
     *
     * @return
     */

    private static void calculate(List<Integer> leftIndex, List<Integer> rightIndex, List<Integer[]> position) {

        int size = leftIndex.size();
        if (size == 1) {
            Integer[] p = new Integer[2];
            p[0] = leftIndex.get(0);
            p[1] = rightIndex.get(0);
            position.add(p);
            return;
        }
        int pos = 0;
        int tmpPos = pos;
        while (rightIndex.get(tmpPos) > leftIndex.get(size - 1 - pos)) {
            if (leftIndex.get(size - 1 - pos) > rightIndex.get(tmpPos + 1)) {
                break;
            }
            tmpPos++;
        }
        if (leftIndex.get(size - 1 - tmpPos) < rightIndex.get(tmpPos + 1)) {
            pos = tmpPos;
            calculate(leftIndex.subList(0, size - 1 - pos), rightIndex.subList(pos + 1, size), position);
        } else {
            Integer[] p = new Integer[2];
            p[0] = leftIndex.get(0);
            p[1] = rightIndex.get(tmpPos);
            position.add(p);
            calculate(leftIndex.subList(1, size - 1 - pos), rightIndex.subList(pos + 1, size), position);
        }
        calculate(leftIndex.subList(size - 1 - pos, size), rightIndex.subList(0, tmpPos + 1), position);
    }

    public static void main(String[] args) {
//        String text = "((())())()()";
//        List resolve = resolve(text, "(", ")");
//        for (int i = 0; i < resolve.size(); i++) {
//            System.out.println(JSON.toJSONString(resolve.get(i)));
//        }
//        System.out.println();

        String text1 = "((())())(())";
        List resolve1 = resolve(text1, "(", ")");
        for (int i = 0; i < resolve1.size(); i++) {
            System.out.println(JSON.toJSONString(resolve1.get(i)));
        }
    }

}
