package com.derbysoft.java2as3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

abstract class JavaTypeAndActionscriptTypeMapping {

    private static final Map<Class, String> JAVA_AND_AS_TYPE_NAME_MAPPING = new HashMap<Class, String>();

    private static final Map<Class, String> JAVA_AND_AS_TYPE_NAME_WITH_PACAGE_MAPPING = new HashMap<Class, String>();

    public static String getAsTypeName(Class clazz) {
        String actionscriptType = JAVA_AND_AS_TYPE_NAME_MAPPING.get(clazz);
        if (actionscriptType != null) {
            return actionscriptType;
        }
        return clazz.getSimpleName();
    }

    public static String getAsTypeNameWithPackage(Class clazz) {
        return JAVA_AND_AS_TYPE_NAME_WITH_PACAGE_MAPPING.get(clazz);
    }

    static {
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(String.class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Character.class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(char.class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Character[].class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(char[].class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(BigDecimal.class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(BigInteger.class, "String");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(int.class, "int");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(byte.class, "int");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(byte[].class, "int");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(short.class, "int");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(long.class, "uint");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Integer.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Long.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Float.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Double.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Byte.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(float.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(double.class, "Number");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Boolean.class, "Boolean");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(boolean.class, "Boolean");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Calendar.class, "Date");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Date.class, "Date");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Byte[].class, "ByteArray");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Collection.class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(List.class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(ArrayList.class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Object[].class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Set.class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(HashSet.class, "Array");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Map.class, "Object");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(HashMap.class, "Object");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(org.w3c.dom.Document.class, "XML object");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(null, "null");
        JAVA_AND_AS_TYPE_NAME_MAPPING.put(Object.class, "Typed Object");

        JAVA_AND_AS_TYPE_NAME_WITH_PACAGE_MAPPING.put(Byte[].class, "flash.utils.ByteArray");
    }
}
