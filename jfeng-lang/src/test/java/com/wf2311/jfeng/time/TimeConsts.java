package com.wf2311.jfeng.time;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangfeng
 * @time 2017/04/21 15:29.
 */
public class TimeConsts {
    public final static Map<FormatterStyle, String> map = new HashMap<>();

    static {
        map.put(FormatterStyle.YYYY_MM_DD_HH_MM_SS, "2017-04-21 13:27:59");
        map.put(FormatterStyle.SLASH_YYYY_MM_DD, "2017/04/21");
        map.put(FormatterStyle.YYYY_MM_DD, "2017-04-21");
        map.put(FormatterStyle.SLASH_YYYY_MM_DD_HH_MM_SS, "2017/04/21 13:27:59");
        map.put(FormatterStyle.YYYY_MM, "2017-04");
        map.put(FormatterStyle.YYYY_MM_DD_HH_MM, "2017-04-21 13:27");
        map.put(FormatterStyle.YYYY_MM_DD_HH, "2017-04-21 13");
        map.put(FormatterStyle.YYYYMM, "201704");
        map.put(FormatterStyle.YYYYMMDD, "20170421");
        map.put(FormatterStyle.YYYYMMDDHH, "2017042113");
        map.put(FormatterStyle.YYYYMMDDHHMM, "201704211327");
        map.put(FormatterStyle.YYYYMMDDHHMMSS, "20170421132759");
        map.put(FormatterStyle.SLASH_YYYY_MM, "2017/04");
        map.put(FormatterStyle.SLASH_YYYY_MM_DD_HH, "2017/04/21 13");
        map.put(FormatterStyle.SLASH_YYYY_MM_DD_HH_MM, "2017/04/21 13:27");
        map.put(FormatterStyle.CN_YYYY_MM, "2017年4月");
        map.put(FormatterStyle.CN_YYYY_MM_DD, "2017年4月21日");
        map.put(FormatterStyle.CN2_YYYY_MM_DD, "2017年4月21号");
        map.put(FormatterStyle.CN_YYYY_MM_DD_HH, "2017年4月21日 13点");
        map.put(FormatterStyle.CN_2_YYYY_MM_DD_HH, "2017年4月21日 13时");
        map.put(FormatterStyle.CN2_YYYY_MM_DD_HH, "2017年4月21号 13点");
        map.put(FormatterStyle.CN2_2_YYYY_MM_DD_HH, "2017年4月21号 13时");
        map.put(FormatterStyle.CN_YYYY_MM_DD_HH_MM, "2017年4月21日 13:27");
        map.put(FormatterStyle.CN_2_YYYY_MM_DD_HH_MM, "2017年4月21日 13点27分");
        map.put(FormatterStyle.CN_3_YYYY_MM_DD_HH_MM, "2017年4月21日 13时27分");
        map.put(FormatterStyle.CN2_YYYY_MM_DD_HH_MM, "2017年4月21号 13:27");
        map.put(FormatterStyle.CN2_2_YYYY_MM_DD_HH_MM, "2017年4月21号 13点27分");
        map.put(FormatterStyle.CN2_3_YYYY_MM_DD_HH_MM, "2017年4月21号 13时27分");
        map.put(FormatterStyle.CN_YYYY_MM_DD_HH_MM_SS, "2017年04月21日 13:27:59");
        map.put(FormatterStyle.CN_2_YYYY_MM_DD_HH_MM_SS, "2017年4月21日 13点27分59秒");
        map.put(FormatterStyle.CN_3_YYYY_MM_DD_HH_MM_SS, "2017年04月21日 13时27分59秒");
        map.put(FormatterStyle.CN2_YYYY_MM_DD_HH_MM_SS, "2017年4月21号 13:27:59");
        map.put(FormatterStyle.CN2_2_YYYY_MM_DD_HH_MM_SS, "2017年4月21号 13点27分59秒");
        map.put(FormatterStyle.CN2_3_YYYY_MM_DD_HH_MM_SS, "2017年4月21号 13时27分59秒");
        map.put(FormatterStyle.MM_DD, "04-21");
        map.put(FormatterStyle.HH_MM_SS, "13:27:59");
        map.put(FormatterStyle.SLASH_MM_DD, "04/21");
        map.put(FormatterStyle.CN_MM_DD, "04月21日");
        map.put(FormatterStyle.CN2_MM_DD, "04月21号");
    }
}
