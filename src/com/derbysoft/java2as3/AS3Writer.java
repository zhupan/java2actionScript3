package com.derbysoft.java2as3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

class AS3Writer {

    private static final String DEFAULT_WRITE_TO_FILE = "./as/";

    private String writeToFile = DEFAULT_WRITE_TO_FILE;

    private static final String AS = ".as";

    private static final String DEFAULT_PATTERN = "**/**/*.class";

    public AS3Writer() {
    }

    public AS3Writer(String writeToFile) {
        this.writeToFile = writeToFile;
    }

    public void write(String jarFiles) {
        write(jarFiles, DEFAULT_PATTERN);
    }

    public void write(String jarFiles, String pattern) {
        ClassScanner classScanner = new ClassScanner(jarFiles, pattern);
        List<Class> classes = classScanner.getAllClass();
        for (Class clazz : classes) {
            write(clazz);
        }
    }

    public void write(Class clazz) {
        String source = new JavaGenerateAS3().generateASSource(clazz);
        writeSourceToFile(generateASFileName(clazz), source);
    }

    private void writeSourceToFile(String fileName, String source) {
        File file = new File(fileName);
        RandomAccessFile dos = null;
        try {
            dos = new RandomAccessFile(file, "rw");
            dos.write(source.toString().getBytes());
        } catch (FileNotFoundException e) {
            String msg = "file[" + file + "] not found";
            throw new RuntimeException(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String generateASFileName(Class clazz) {
        mkdirForAS(clazz);
        String classFolder = writeToFile + clazz.getPackage().getName().replace(Separator.DOT, Separator.SLASH);
        String fileName = classFolder + Separator.SLASH + clazz.getSimpleName() + AS;
        return fileName;
    }

    private void mkdirForAS(Class clazz) {
        String classFolder = writeToFile + clazz.getPackage().getName().replace(Separator.DOT, Separator.SLASH);
        String[] pakages = classFolder.split(Separator.SLASH);
        String folder = "";
        for (String pakage : pakages) {
            if (folder == "") {
                folder = pakage;
            } else {
                folder = folder + Separator.SLASH + pakage;
            }
            if (!(new File(folder).isDirectory())) {
                try {
                    new File(folder).mkdir();
                } catch (SecurityException e) {
                    throw new RuntimeException("can not make directory[" + folder + "]");
                }
            }
        }
    }

}
