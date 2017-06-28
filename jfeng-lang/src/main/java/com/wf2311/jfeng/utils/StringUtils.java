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



}
