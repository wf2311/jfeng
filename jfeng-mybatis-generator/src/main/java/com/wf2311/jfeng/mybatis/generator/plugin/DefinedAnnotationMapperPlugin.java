package com.wf2311.jfeng.mybatis.generator.plugin;

/**
 * @author wf2311
 */
public class DefinedAnnotationMapperPlugin extends CustomMapperPlugin {

    static {
        annotations.putAll(Const.annotations);
    }
}
