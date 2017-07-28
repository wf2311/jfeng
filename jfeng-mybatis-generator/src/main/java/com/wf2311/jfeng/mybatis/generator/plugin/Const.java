package com.wf2311.jfeng.mybatis.generator.plugin;

import com.wf2311.jfeng.repository.annoation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wf2311
 */
public class Const {
    private Const() {
    }

    static Map<String, Class> annotations = new HashMap<>();

    static {
        annotations.put("@Primary", Primary.class);
        annotations.put("@Creator", Creator.class);
        annotations.put("@CreatedTime", CreatedTime.class);
        annotations.put("@Modifier", Modifier.class);
        annotations.put("@ModifiedTime", ModifiedTime.class);
        annotations.put("@AutoIncrement", AutoIncrement.class);
        annotations.put("@Unique", Unique.class);
    }
}
