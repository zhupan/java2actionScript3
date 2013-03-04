package com.derbysoft.java2as3;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Date;

public class JavaGenerateAS3Test {

    @Test
    public void testGenerateASSource() {
        JavaGenerateAS3 generator = new JavaGenerateAS3();
        String result = generator.generateASSource(ASProperty.class);
        assertEquals(createExpectedSource(), result);
    }

    private String createExpectedSource() {
        StringBuilder expectedSource = new StringBuilder();
        expectedSource.append("/**\n");
        expectedSource.append(" * class name : com.derbysoft.java2as3.ASProperty\n");
        expectedSource.append(" * generate date: " + new Date() + "\n");
        expectedSource.append(" */\n");
        expectedSource.append("package com.derbysoft.java2as3 {\n");
        expectedSource.append("\n");
        expectedSource.append("\t[Bindable]\n");
        expectedSource.append("\t[RemoteClass(alias=\"com.derbysoft.java2as3.ASProperty\")]\n");
        expectedSource.append("\tpublic class ASProperty {\n");
        expectedSource.append("\n");
        expectedSource.append("\t\tpublic var modifier:String;\n");
        expectedSource.append("\t\tpublic var name:String;\n");
        expectedSource.append("\t\tpublic var type:String;\n");
        expectedSource.append("\t\tpublic var enumPresentation:Boolean;\n");
        expectedSource.append("\n");
        expectedSource.append("\t}\n");
        expectedSource.append("}");
        return expectedSource.toString();
    }

}
