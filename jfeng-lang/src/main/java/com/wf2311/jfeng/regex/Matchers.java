package com.wf2311.jfeng.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wf2311
 * @time 2017/5/20 17:11.
 */
public final class Matchers {

    /**
     * 从<code>text</code>中匹配第一个符合正则表达式的字符串
     */
    public static String match(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

}
