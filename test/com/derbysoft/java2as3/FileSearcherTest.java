package com.derbysoft.java2as3;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileSearcherTest {

    @Test
    public void test() {
        List<File> files = new FileSearcher().getFiles("*.class");
        boolean contains = false;
        for (File file : files) {
            if (file.getName().contains("FileSearcherTest.class")) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }
}
