package com.wf2311.jfeng.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<int[]> position = new ArrayList<>();
        calculate(leftIndex, rightIndex, position);

        return position;
    }

    /**
     * 情况1：
     * <code>
     * 1、 2、 3、6、9、11
     * 12、10、8、7、5、4
     * </code>
     * 情况2：
     * <code>
     * 1、 2、 3、6、9、10
     * 12、11、8、7、5、4
     * </code>
     *
     * @return
     */

    private static void calculate(List<Integer> leftIndex, List<Integer> rightIndex, List<int[]> position) {
        int size = leftIndex.size();
        int subIndex = size - 1;
        int i = 0;
        while (rightIndex.get(i) > leftIndex.get(subIndex)) {
            i++;
            subIndex--;
        }
        calculate(leftIndex.subList(subIndex, size), rightIndex.subList(0, i), position);
        if (i < rightIndex.size()) {
            int[] p = new int[2];
            p[0] = leftIndex.get(0);
            p[1] = rightIndex.get(i);
            position.add(p);
            calculate(leftIndex.subList(1, subIndex), rightIndex.subList(i, size), position);
        }

    }

    public static void main(String[] args) {
        String text = "((())())()()";
        System.out.println(resolve(text, "(", ")"));
    }

}
