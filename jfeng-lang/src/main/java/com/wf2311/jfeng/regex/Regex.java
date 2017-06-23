package com.wf2311.jfeng.regex;

/**
 * @author wf2311
 */
public interface Regex {
    /**
     * 匹配月份：1~9或01~12
     */
    String MONTH = "(0?[1-9]|1[0-2])";

    /**
     * 匹配月天：1~9或01~31
     */
    String DAY_OF_MONTH = "((0?[1-9])|((1|2)[0-9])|30|31)";

    /**
     * 匹配小时：0~9或00~24
     */
    String HOUR = "([0-9]|[01][0-9]|2[0-4])";

    /**
     * 匹配分钟：0~9或00~59
     */
    String MINUTE = "([0-9]|[0-5][0-9])";

    /**
     * 匹配分钟：0~9或00~59
     */
    String SECOND = MINUTE;

    /**
     * 匹配单个汉字
     */
    String ONE_CHINESE = "([\\u0391-\\uFFE5])";

    /**
     * 匹配汉字
     */
    String CHINESE = "([\\u0391-\\uFFE5]+)";

    /**
     * 匹配(中国大陆)手机号码
     */
    String MOBILE = "(1[3|4|5|7|8][0-9]{9})";

    /**
     * 匹配电子邮箱
     */
    String EMAIL = "([a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+)";

    /**
     * 结束句子的标点符号
     */
    String PUNCTUATION_OF_END_SENTENCE = "：，。；！……,.:;.\n\t";

    /**
     * 匹配句子
     */
    String SENTENCE = "([^" + PUNCTUATION_OF_END_SENTENCE + "]+[" + PUNCTUATION_OF_END_SENTENCE + "]+)";

}

