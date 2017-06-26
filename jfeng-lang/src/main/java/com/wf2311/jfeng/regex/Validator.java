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

}
