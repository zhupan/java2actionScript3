package com.derbysoft.java2as3;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

class JavaGenerateAS3 {

    private static PathMatcher pathMatcher = new AntPathMatcher();

    private static final String DEFAULT_IGNORE_CLASS_PATTERN = "OvalValidateSupport";

    private static final String ENUM_VALUES = "$VALUES";
    
    private static final int ENUM_SEPARATE_SIZE = 5;

    public String generateASSource(Class<?> clazz) {
        StringBuffer source = new StringBuffer();
        String className = clazz.getName();
        String shortClassName = clazz.getSimpleName();
        Package packageClass = clazz.getPackage();
        source.append(generateAnnotate(className));
        source.append("package " + packageClass.getName() + " {\n");
        source.append(printImported(generateShouldImportedClass(clazz)));
        source.append(printImportedASClass(generateShouldImportedASClass(clazz)));
        source.append("\n\t[Bindable]\n");
        source.append("\t[RemoteClass(alias=\"" + className + "\")]\n");
        source.append("\tpublic class " + shortClassName);
        if (hasSupperclass(clazz) && !isIgnoreClass(clazz.getSuperclass())) {
            source.append(" extends " + clazz.getSuperclass().getSimpleName());
        }
        source.append(" {\n\n");
        source.append(printProperties(generateASProperties(clazz)));
        source.append(printEnumMethod(clazz));
        source.append("\n\t}");
        source.append("\n}");
        return source.toString();
    }

    private String printEnumMethod(Class<?> clazz) {
        if (clazz.isEnum()) {                  
            StringBuffer source = new StringBuffer();
            source.append("\n\t\t" + "public static function allValue() : Array" + "\n");
            source.append("\t\t{\n");
            source.append("\t\t\t" + "return [" + getEnumValues(clazz) + "];\n");
            source.append("\t\t}\n");
            return source.toString();
        }
        return "";
    }

    private String getEnumValues(Class<?> clazz) {
        List<String> names = new ArrayList<String>();
        for (Field field : clazz.getFields()) {
            names.add(field.getName());
        }
        Object[] namesArray = names.toArray();
        for (int i = 1; i < namesArray.length; i++) {
            if (i % ENUM_SEPARATE_SIZE == 0) {
                namesArray[i] = "\n\t\t\t\t" + namesArray[i].toString();
            }
        }
        String namestrs = StringUtils.join(namesArray, Separator.COMMA);
        return namestrs;
    }

    private String printImportedASClass(Set<String> asClass) {
        if (CollectionUtils.isEmpty(asClass)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (String clazz : asClass) {
            builder.append("\t");
            builder.append("import ");
            builder.append(clazz);
            builder.append(";\n");
        }
        return builder.toString();
    }

    private Set<String> generateShouldImportedASClass(Class<?> clazz) {
        Set<String> classes = new LinkedHashSet<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().getName().equals(Byte[].class.getName())) {
                classes.add(JavaTypeAndActionscriptTypeMapping.getAsTypeNameWithPackage(Byte[].class));
            }
        }
        return classes;
    }

    private boolean isIgnoreClass(Class<?> clazz) {
        return pathMatcher.match(DEFAULT_IGNORE_CLASS_PATTERN, clazz.getSimpleName());
    }

    private String printProperties(Collection<ASProperty> generateASProperties) {
        StringBuilder builder = new StringBuilder();
        for (ASProperty property : generateASProperties) {
            builder.append("\t\t" + property.print() + "\n");
        }
        return builder.toString();
    }

    private String printImported(Set<Class<?>> classes) {
        if (CollectionUtils.isEmpty(classes)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Class<?> clazz : classes) {
            builder.append("\t");
            builder.append("import ");
            builder.append(clazz.getName());
            builder.append(";\n");
        }
        return builder.toString();
    }

    private Collection<ASProperty> generateASProperties(Class<?> clazz) {
        Collection<ASProperty> properties = new ArrayList<ASProperty>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (isPropertyRequired(clazz, field)) {
                ASProperty property = new ASProperty();
                property.setModifier("public var");
                property.setName(field.getName());
                property.setType(getMappedASType(field.getType()));
                property.setEnumPresentation(clazz.isEnum());
                properties.add(property);
            }
        }
        return properties;
    }

    private boolean isPropertyRequired(Class<?> belongto, Field field) {
        if (belongto.isEnum()) {
            return Modifier.isStatic(field.getModifiers()) && !ENUM_VALUES.equals(field.getName());
        }
        return !Modifier.isStatic(field.getModifiers());
    }

    private boolean hasSupperclass(Class<?> clazz) {
        return !clazz.getSuperclass().equals(Object.class) && !clazz.isEnum();
    }

    private StringBuffer generateAnnotate(String className) {
        StringBuffer asSource = new StringBuffer();
        asSource.append("/**\n");
        asSource.append(" * class name : " + className + "\n");
        asSource.append(" * generate date: " + new Date() + "\n");
        asSource.append(" */\n");
        return asSource;
    }

    private Set<Class<?>> generateShouldImportedClass(Class clazz) {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        Field[] fields = clazz.getDeclaredFields();
        if (clazz.isEnum()) {
            return classes;
        }
        if (isIgnoreClass(clazz.getSuperclass())) {
            return classes;
        }
        if (!clazz.getSuperclass().equals(Object.class)) {
            classes.add(clazz.getSuperclass());
        }
        for (Field field : fields) {
            if (!ClassUtils.isBasicJavaType(field.getType())) {
                classes.add(field.getType());
            }
        }
        return classes;
    }

    private String getMappedASType(Class<?> fieldType) {
        if (fieldType.isEnum()) {
            return "String";
        }
        return JavaTypeAndActionscriptTypeMapping.getAsTypeName(fieldType);
    }

}