package com.wf2311.jfeng.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wf2311
 * @time 2017/5/20 17:11.
 */
public class Matchers {

    public static String match(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(string);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

}
