package com.wf2311.jfeng.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wf2311.jfeng.regex.Regex.*;

/**
 * @author wf2311
 */
public final class Matchers {

    private Matchers() {
    }

    /**
     * 判读正则表达式中最外围是否用()包裹，从而确定匹配结果在<code>Matcher</>中第一次出现的位置
     *
     * @throws IllegalArgumentException 当正则表达式不正确是，抛出此异常
     */
    private static int group(String regex) {
        if (regex.startsWith("^(") || regex.startsWith("(")) {
            if (!regex.endsWith(")") && !regex.endsWith(")$")) {
                throw new IllegalArgumentException("错误的正则表达式");
            }
            return 1;
        }
        return 0;
    }

    /**
     * 匹配第一个符合正则表达式的子字符串
     */
    public static String match(String text, String regex) {
        int group = group(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            return m.group(group);
        }
        return null;
    }

    /**
     * 匹配所有符合正则表达式的子字符串
     */
    public static List<String> matchAll(String text, String regex) {
        return matchAll(text, regex, false);
    }

    /**
     * 匹配所有符合正则表达式的子字符串
     */
    public static List<String> matchAll(String text, String regex, boolean trim) {
        int group = group(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(text);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(trim ? m.group(group).trim() : m.group(group));
        }
        return result;
    }

    /**
     * 按标点符号切割成语句
     */
    public static List<String> subSentence(String text) {
        return matchAll(text, SENTENCE, true);
    }

    /**
     * 匹配第一个出现的手机号码
     */
    public static String mobileMobile(String text) {
        return match(text, MOBILE);
    }

    /**
     * 匹配所有的手机号码
     */
    public static List<String> matchAllMobile(String text) {
        return matchAll(text, MOBILE);
    }

    /**
     * 匹配第一个出现的电子邮箱
     */
    public static String matchEmail(String text) {
        return match(text, MOBILE);
    }

    /**
     * 匹配所有的电子邮箱
     */
    public static List<String> matchAllEmail(String text) {
        return matchAll(text, MOBILE);
    }

}
