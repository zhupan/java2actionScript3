package com.derbysoft.java2as3;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: ./lib/derbysoft/derbysoft-remote-*.jar");
        }
        if (args.length == 1) {
            System.out.println("args length is 1,start generate as3");
            System.out.println("pattern is " + args[0]);
            new AS3Writer().write(getFileNames(args[0]));
        }
        if (args.length == 2) {
            System.out.println("args length is 2,start generate as3");
            System.out.println("pattern is " + args[0]);
            System.out.println("out put path is " + args[1]);
            new AS3Writer(args[1]).write(getFileNames(args[0]));
        }
        System.out.println("end generate as3");
    }

    private static String getFileNames(String pathPattern) {
        int index = pathPattern.lastIndexOf(Separator.SLASH);
        List<File> files = new FileSearcher().getFiles(pathPattern.substring(0, index), pathPattern.substring(index + 1));
        return StringUtils.join(files, Separator.COMMA);
    }

}
