package mago.config;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class ReflectionUtils {
    public static <T> void setField(T bean, String prop, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = getDeclaredField(bean.getClass(), prop);
        declaredField.setAccessible(true);
        if (Enum.class.isAssignableFrom(declaredField.getType())) {
            declaredField.set(bean, Enum.valueOf(((Class) declaredField.getType()), (String)value));
        } else {
            declaredField.set(bean, value);
        }
        declaredField.setAccessible(false);
    }

    public static <U> U getField(Object bean, String prop) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = getDeclaredField(bean.getClass(), prop);
        declaredField.setAccessible(true);
        U result = (U) declaredField.get(bean);
        declaredField.setAccessible(false);
        return result;
    }

    public static Class getFieldType(Object bean, String prop) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = getDeclaredField(bean.getClass(), prop);
        return declaredField.getType();
    }

    public static Class getFieldGenericType(Object bean, String prop) throws NoSuchFieldException, IllegalAccessException {
        return getFieldGenericType(bean, prop, 0);
    }

    public static Class getFieldGenericType(Object bean, String prop, int paramPos) throws NoSuchFieldException, IllegalAccessException {
        Type type = getDeclaredField(bean.getClass(), prop).getGenericType();
        return type instanceof ParameterizedType
                ? (Class) ((ParameterizedType)type).getActualTypeArguments()[paramPos]
                : null;
    }

    public static boolean hasField(Object bean, String prop) {
        try {
            getDeclaredField(bean.getClass(), prop);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static <T> Field getDeclaredField(Class<?> aClass, String prop) throws NoSuchFieldException {
        try {
            return aClass.getDeclaredField(prop);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = aClass.getSuperclass();
            if (superClass == null) {
                throw e;
            }
            return getDeclaredField(superClass, prop);
        }
    }
}
