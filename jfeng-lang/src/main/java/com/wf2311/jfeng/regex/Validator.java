package com.wf2311.jfeng.regex;

import static com.wf2311.jfeng.regex.Regex.*;

/**
 * @author wf2311
 */
public final class Validator {

    private Validator() {
    }

    /**
     * 判断文本是否全匹配指定正则表达式
     */
    public static boolean fullMatch(String text, String regex) {
        StringBuilder sb = new StringBuilder();
        if (isBlank(regex)) {
            return false;
        }
        if (!regex.startsWith("^")) {
            sb.append("^");
        }
        sb.append(regex);
        if (!regex.endsWith("$")) {
            sb.append("$");
        }
        return text.matches(sb.toString());
    }

    /**
     * 判断文本是否是电子邮箱
     */
    public static boolean isEmail(String text) {
        return fullMatch(text, EMAIL);
    }

    /**
     * 判断文本是否是手机号码
     */
    public static boolean isMobile(String text) {
        return fullMatch(text, MOBILE);
    }

    /**
     * 判断文本是否是汉字
     */
    public static boolean isChinese(String text) {
        return fullMatch(text, CHINESE);
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
