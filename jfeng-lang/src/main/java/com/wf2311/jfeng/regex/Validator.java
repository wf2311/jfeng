package com.wf2311.jfeng.regex;

import com.wf2311.jfeng.utils.StringUtils;

import static com.wf2311.jfeng.regex.Regex.*;
import static com.wf2311.jfeng.regex.Matchers.wrapBracket;

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
        if (StringUtils.isBlank(regex)) {
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
        return fullMatch(text, wrapBracket(EMAIL));
    }

    /**
     * 判断文本是否是手机号码
     */
    public static boolean isMobile(String text) {
        return fullMatch(text, wrapBracket(MOBILE));
    }

    /**
     * 判断文本是否是汉字
     */
    public static boolean isChinese(String text) {
        return fullMatch(text, wrapBracket(CHINESE));
    }

    /**
     * 判断文本是否是用户名（字母开头 + 数字/字母/下划线）
     */
    public static boolean isUserName(String text) {
        return fullMatch(text, wrapBracket(USERNAME));
    }

    /**
     * 判断文本是否是用户名(字母开头 + 数字/字母/下划线)并且长度在指定范围内
     */
    public static boolean isUserName(String text, int minLength, int maxLength) {
        return isUserName(text) && isLengthIn(text, minLength, maxLength);
    }

    /**
     * 判断文本是否大于等于指定长度
     */
    public static boolean minLength(String text, int minLength) {
        return getTextLength(text) >= minLength;
    }

    /**
     * 判断文本是否小于等于指定长度
     */
    public static boolean maxLength(String text, int maxLength) {
        return getTextLength(text) <= maxLength;
    }

    /**
     * 判断文本是否在指定范围长度内
     */
    public static boolean isLengthIn(String text, int minLength, int maxLength) {
        int length = getTextLength(text);
        return length >= minLength && length <= maxLength;
    }

    /**
     * 判断文本是否是邮政编码
     */
    public static boolean isZipCode(String text) {
        return fullMatch(text, ZIP_CODE);
    }

    /**
     * 判断文本是否是身份证号
     */
    public static boolean isIdCard(String text) {
        return fullMatch(text, ID_CARD);
    }

    /**
     * 判断文本是否是QQ号
     */
    public static boolean isQQ(String text) {
        return fullMatch(text, QQ);
    }

    /**
     * 判断文本是否是URL
     */
    public static boolean isUrl(String text){
        return fullMatch(text, URL);
    }

    /**
     * 得到文本(首尾的空格)的长度
     *
     * @throws NullPointerException
     */
    public static int getTextLength(String text) {
        if (text == null) {
            throw new NullPointerException();
        }
        return text.trim().length();
    }

}
