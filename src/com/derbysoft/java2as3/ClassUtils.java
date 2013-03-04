package com.derbysoft.java2as3;

import org.springframework.util.Assert;
import static org.springframework.util.ClassUtils.*;

import java.math.BigDecimal;
import java.util.*;

abstract class ClassUtils {

    private static final List<Class> BASIC_JAVA_TYPES = Collections.unmodifiableList((List<Class>) Arrays.asList(new Class[]{
            Class.class,
            String.class,
            Date.class,
            java.sql.Date.class,
            BigDecimal.class
    }));

    public static boolean isBasicJavaType(Class<?> clazz) {
        Assert.notNull(clazz, "clazz required");
        boolean isPrimitive = isPrimitiveOrWrapper(clazz) || isPrimitiveArray(clazz) || isPrimitiveWrapperArray(clazz);
        if (isPrimitive) {
            return true;
        }
        if (BASIC_JAVA_TYPES.contains(clazz)) {
            return true;
        }
        if (isCollectionOrMap(clazz)) {
            return true;
        }
        if (clazz.isArray()) {
            Class<?> componentType = clazz.getComponentType();
            while (componentType.isArray()) {
                componentType = componentType.getComponentType();
            }
            return isSingleJavaType(componentType);
        }
        return false;
    }


    private static boolean isCollectionOrMap(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz);
    }

    private static boolean isSingleJavaType(Class<?> clazz) {
        return BASIC_JAVA_TYPES.contains(clazz) || isPrimitiveOrWrapper(clazz);
    }
}
