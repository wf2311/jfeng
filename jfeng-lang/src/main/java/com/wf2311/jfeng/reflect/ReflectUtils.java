package com.wf2311.jfeng.reflect;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author wf2311
 */
public class ReflectUtils {

    /**
     * Empty class array.
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];

    public static final String METHOD_GET_PREFIX = "get";
    public static final String METHOD_IS_PREFIX = "is";
    public static final String METHOD_SET_PREFIX = "set";


    /**
     * Returns classes from array of objects. It accepts {@code null}
     * values.
     */
    public static Class[] getClasses(Object... objects) {
        if (objects.length == 0) {
            return EMPTY_CLASS_ARRAY;
        }
        Class[] result = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                result[i] = objects[i].getClass();
            }
        }
        return result;
    }

    /**
     * Finds constructor with given parameter types. First matched ctor is returned.
     */
    public static <T> Constructor<T> findConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        final Constructor<?>[] constructors = clazz.getConstructors();

        Class<?>[] pts;

        for (Constructor<?> constructor : constructors) {
            pts = constructor.getParameterTypes();

            if (isAllAssignableFrom(pts, parameterTypes)) {
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    /**
     * Returns {@code true} if all types are assignable from the other array of types.
     */
    public static boolean isAllAssignableFrom(Class<?>[] typesTarget, Class<?>[] typesFrom) {
        if (typesTarget.length == typesFrom.length) {
            for (int i = 0; i < typesTarget.length; i++) {
                if (!typesTarget[i].isAssignableFrom(typesFrom[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Creates new instance of given class with given optional arguments.
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object... params) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (params.length == 0) {
            return newInstance(clazz);
        }

        final Class<?>[] paramTypes = getClasses(params);

        final Constructor<?> constructor = findConstructor(clazz, paramTypes);

        if (constructor == null) {
            throw new InstantiationException("No constructor matched parameter types.");
        }

        return (T) constructor.newInstance(params);
    }

    /**
     * Creates new instances including for common mutable classes that do not have a default constructor.
     * more user-friendly. It examines if class is a map, list,
     * String, Character, Boolean or a Number. Immutable instances are cached and not created again.
     * Arrays are also created with no elements. Note that this bunch of <code>if</code> blocks
     * is faster then using a <code>HashMap</code>.
     *
     * @param type
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> type) throws IllegalAccessException, InstantiationException {
        if (type.isPrimitive()) {
            if (type == int.class) {
                return (T) Integer.valueOf(0);
            }
            if (type == long.class) {
                return (T) Long.valueOf(0);
            }
            if (type == boolean.class) {
                return (T) Boolean.FALSE;
            }
            if (type == float.class) {
                return (T) Float.valueOf(0);
            }
            if (type == double.class) {
                return (T) Double.valueOf(0);
            }
            if (type == byte.class) {
                return (T) Byte.valueOf((byte) 0);
            }
            if (type == short.class) {
                return (T) Short.valueOf((short) 0);
            }
            if (type == char.class) {
                return (T) Character.valueOf((char) 0);
            }
            throw new IllegalArgumentException("Invalid primitive: " + type);
        }
        if (type == Integer.class) {
            return (T) Integer.valueOf(0);
        }
        if (type == String.class) {
            return (T) "";
        }
        if (type == Long.class) {
            return (T) Long.valueOf(0);
        }
        if (type == Boolean.class) {
            return (T) Boolean.FALSE;
        }
        if (type == Float.class) {
            return (T) Float.valueOf(0);
        }
        if (type == Double.class) {
            return (T) Double.valueOf(0);
        }

        if (type == Map.class) {
            return (T) new HashMap(16);
        }
        if (type == List.class) {
            return (T) new ArrayList();
        }
        if (type == Set.class) {
            return (T) new HashSet();
        }
        if (type == Collection.class) {
            return (T) new ArrayList();
        }

        if (type == Byte.class) {
            return (T) Byte.valueOf((byte) 0);
        }
        if (type == Short.class) {
            return (T) Short.valueOf((short) 0);
        }
        if (type == Character.class) {
            return (T) Character.valueOf((char) 0);
        }

        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }

        if (type.isArray()) {
            return (T) Array.newInstance(type.getComponentType(), 0);
        }

        return type.newInstance();
    }


    /**
     * @param object 对象实例
     * @param field  成员变量
     * @param value  要赋予的值
     * @throws IllegalAccessException
     */
    public static void setFiledValue(Object object, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @param value     要赋予的值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setFiledValue(Object object, String filedName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getFiled(object, filedName);
        setFiledValue(object, field, value);
    }

    /**
     * @param object 对象实例
     * @param field  成员变量
     * @return 成员变量值
     * @throws IllegalAccessException
     */
    public static Object getFiledValue(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @return 成员变量值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFiledValue(Object object, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field filed = getFiled(object, filedName);
        return getFiledValue(object, filed);
    }

    /**
     * @param object    对象实例
     * @param filedName 成员变量名
     * @return 成员变量域
     * @throws NoSuchFieldException
     */
    public static Field getFiled(Object object, String filedName) throws NoSuchFieldException {
        return object.getClass().getDeclaredField(filedName);
    }

}
