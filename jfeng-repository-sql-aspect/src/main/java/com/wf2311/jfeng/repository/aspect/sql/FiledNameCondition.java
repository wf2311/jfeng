package com.wf2311.jfeng.repository.aspect.sql;

import com.wf2311.jfeng.repository.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 * @author wangfeng
 * @time 2017/08/23 14:43.
 */
public class FiledNameCondition implements Condition {
    private static final Logger logger = Logger.getLogger(FiledNameCondition.class.getName());
    /**
     * 是否要设置主键值
     *
     * @param obj
     * @param field
     */
    @Override
    public boolean shouldSetPrimary(Object obj, Field field) {
        return false;
    }

    /**
     * 是否要设置创建者
     *
     * @param obj
     * @param field
     */
    @Override
    public boolean shouldSetCreator(Object obj, Field field) {
        return false;
    }

    /**
     * 是否要设置创建时间
     *
     * @param obj
     * @param field
     */
    @Override
    public boolean shouldSetCreatedTime(Object obj, Field field) {
        return false;
    }

    /**
     * 是否要设置修改者
     *
     * @param obj
     * @param field
     */
    @Override
    public boolean shouldSetModifier(Object obj, Field field) {
        return false;
    }

    /**
     * 是否要设置修改时间
     *
     * @param obj
     * @param field
     */
    @Override
    public boolean shouldSetModifiedTime(Object obj, Field field) {
        return false;
    }

    private static boolean showSetValue(Object obj,Field field,String name){
        if (name.equals(field.getName())) {
            try {
                return ReflectUtils.getFiledValue(obj, field) == null;
            } catch (IllegalAccessException e) {
                logger.warning(e.getMessage());
            }
        }
        return false;
    }
}
