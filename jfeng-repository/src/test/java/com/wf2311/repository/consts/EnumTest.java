package com.wf2311.repository.consts;


import com.wf2311.jfeng.reflect.ClassUtils;
import com.wf2311.repository.annoation.EnableAccessEnum;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangfeng
 * @time 2017/06/22 17:05.
 */
public class EnumTest {

    @Test
    public void test1(){
        String packageName = "com.wf2311.repository";
//        packageName = "com.fasterxml.classmate";
        List<Class<?>> classes = ClassUtils.getClasses(packageName);
        classes.forEach(c-> System.out.println(c.getName()));

        System.out.println("========================\n\n\n");
        List<Class<?>> list = ClassUtils.getClasses(packageName, EnableAccessEnum.class);
        list.forEach(c-> System.out.println(c.getName()));
        list.forEach(c->{
            if (c.isEnum()) {
                Arrays.stream(c.getEnumConstants()).forEach(d->{
                    Class<?> aClass = d.getClass();
                    TypeVariable<? extends Class<?>>[] typeParameters = aClass.getTypeParameters();
                    Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
                    Field[] declaredFields = aClass.getDeclaredFields();
                    
                    Method[] methods = aClass.getDeclaredMethods();
                    System.out.println(methods.length);
                    System.out.println(d.toString());
                });
            }
        });
    }
}
