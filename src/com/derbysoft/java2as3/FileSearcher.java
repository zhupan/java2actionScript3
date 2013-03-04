package com.derbysoft.java2as3;

import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearcher {

    public List<File> getFiles(String fileName) {
        return getFiles("./", fileName);
    }

    public List<File> getFiles(String fileFolder, String fileName) {
        File file = new File(fileFolder);
        fileName = fileName.replace('.', '#');
        fileName = fileName.replaceAll("#", "\\\\.");
        fileName = fileName.replace('*', '#');
        fileName = fileName.replaceAll("#", ".*");
        fileName = fileName.replace('?', '#');
        fileName = fileName.replaceAll("#", ".?");
        fileName = "^" + fileName + "$";
        Pattern pattern = Pattern.compile(fileName);
        return filePattern(file, pattern);
    }

    private List<File> filePattern(File file, Pattern pattern) {
        if (file == null) {
            return null;
        }
        if (file.isFile()) {
            Matcher fMatcher = pattern.matcher(file.getName());
            if (fMatcher.matches()) {
                List<File> list = new ArrayList<File>();
                list.add(file);
                return list;
            }
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                List<File> result = new ArrayList<File>();
                for (int i = 0; i < files.length; i++) {
                    List<File> list = filePattern(files[i], pattern);
                    if (CollectionUtils.isNotEmpty(list)) {
                        result.addAll(list);
                    }
                }
                return result;
            }
        }
        return null;
    }
}
