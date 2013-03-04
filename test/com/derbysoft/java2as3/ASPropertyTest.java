package com.derbysoft.java2as3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ASPropertyTest {

    @Test
    public void testPrintIntProperty() {
        String result = createASProperty("int", false).print();
        String expectedPrintStr = "public var orderID:int;";
        assertEquals(expectedPrintStr, result);
    }
    
    @Test
    public void testPrintEnumProperty() {
        String result = createASProperty("String", true).print();
        String expectedPrintStr = "static public var orderID:String = \"orderID\";";
        assertEquals(expectedPrintStr, result);
    }

    private ASProperty createASProperty(String type, boolean isEnum) {
        ASProperty asProperty = new ASProperty();
        asProperty.setName("orderID");
        asProperty.setModifier("public var");
        asProperty.setType(type);
        asProperty.setEnumPresentation(isEnum);
        return asProperty;
    }

}
