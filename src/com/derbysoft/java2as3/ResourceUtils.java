package com.derbysoft.java2as3;

abstract class ResourceUtils {

    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    public static final String JAVA_CLASS_PATH = "java.class.path";

    public static final String CLASS_PATHS = System.getProperty(JAVA_CLASS_PATH);

    public static void setSystemClassPath(String filePaths) {
        String[] files = filePaths.split(",");
        for (String file : files) {
            System.setProperty(JAVA_CLASS_PATH, new StringBuilder(CLASS_PATHS)
                    .append(PATH_SEPARATOR)
                    .append(file)
                    .toString());
        }
    }

}
