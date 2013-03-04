package com.derbysoft.java2as3;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class ClassScanner {

    private String jarFiles;

    private String pattern;

    private static PathMatcher pathMatcher = new AntPathMatcher();

    public ClassScanner(String jarFiles, String pattern) {
        this.jarFiles = jarFiles;
        this.pattern = pattern;
    }

    public List<Class> getAllClass() {
        ResourceUtils.setSystemClassPath(jarFiles);
        List<Class> classes = new ArrayList<Class>();
        for (String file : jarFiles.split(Separator.COMMA)) {
            for (String className : findResourcesInJar(file, pattern)) {
                className = className.replace(Separator.SLASH, Separator.DOT);
                className = className.substring(0, className.indexOf(".class"));
                Class clazz = null;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("class[" + className + "] not found.");
                }
                if (!clazz.isInterface()) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }

    private Set<String> findResourcesInJar(String jarFile, String pattern) {
        JarFile file = null;
        Set<String> result = new TreeSet<String>();
        try {
            file = new JarFile(jarFile);
        } catch (IOException e) {
            String msg = "open jar file[" + jarFile + "] error";
            throw new RuntimeException(msg);
        }
        Enumeration<JarEntry> enumeration = file.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry entry = enumeration.nextElement();
            String name = entry.getName();
            if (pathMatcher.match(pattern, name)) {
                result.add(name);
            }
        }
        return result;
    }

}
