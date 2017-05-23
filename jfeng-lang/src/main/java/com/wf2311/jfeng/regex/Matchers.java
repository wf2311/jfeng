package com.wf2311.jfeng.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wf2311
 * @time 2017/5/20 17:11.
 */
public final class Matchers {

    /**
     *
     * @param text
     * @param regex
     * @return
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
