package com.wf2311.jfeng.mybatis.generator.plugin;

/**
 * @author wf2311
 */
@Deprecated
public class DefinedAnnotationJsr310MapperPlugin extends Jsr310MapperPlugin {

    static {
        annotations.putAll(Const.annotations);
    }
}
