package com.wf2311.jfeng.repository.aspect.sql;

import com.wf2311.jfeng.repository.annoation.*;
import com.wf2311.jfeng.repository.utils.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * @author wangfeng
 * @time 2017/08/23 14:33.
 */
public class AnnotationCondition implements Condition {
    private Logger logger = Logger.getLogger(AnnotationCondition.class.getName());

    /**
     * 是否要设置主键值
     *
     */
    @Override
    public boolean shouldSetPrimary(Object obj, Field field) {
        if (field.isAnnotationPresent(Primary.class)
                && !field.isAnnotationPresent(AutoIncrement.class)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.info(e.getMessage());
            }
        }
        return false;
    }

    /**
     * 是否要设置创建者
     */
    @Override
    public boolean shouldSetCreator(Object obj, Field field) {
        return shouldSetValue(obj, field, Creator.class);
    }

    /**
     * 是否要设置创建时间
     */
    @Override
    public boolean shouldSetCreatedTime(Object obj, Field field) {
        return shouldSetValue(obj, field, CreatedTime.class);
    }

    /**
     * 是否要设置修改者
     */
    @Override
    public boolean shouldSetModifier(Object obj, Field field) {
        return shouldSetValue(obj, field, CreatedTime.class);
    }

    /**
     * 是否要设置修改时间
     */
    @Override
    public boolean shouldSetModifiedTime(Object obj, Field field) {
        return shouldSetValue(obj, field, Modifier.class);
    }

    private boolean shouldSetValue(Object obj, Field field, Class<? extends Annotation> annotation) {
        if (field.isAnnotationPresent(annotation)) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.warning(e.getMessage());
            }
        }
        return false;
    }

}
