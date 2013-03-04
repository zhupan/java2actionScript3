package com.derbysoft.java2as3;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class JavaTypeAndActionscriptTypeMappingTest {

    @Test
    public void testGetActionscriptStringType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(String.class);
        assertEquals("String", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(BigDecimal.class);
        assertEquals("String", actionscriptType);
    }

    @Test
    public void testGetActionscriptBooleanType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Boolean.class);
        assertEquals("Boolean", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(boolean.class);
        assertEquals("Boolean", actionscriptType);
    }

    @Test
    public void testGetActionscriptDateType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Date.class);
        assertEquals("Date", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(java.sql.Date.class);
        assertEquals("Date", actionscriptType);
    }

    @Test
    public void testGetActionscriptObjectType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(HashMap.class);
        assertEquals("Object", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Map.class);
        assertEquals("Object", actionscriptType);
    }

    @Test
    public void testGetActionscriptUintType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(long.class);
        assertEquals("uint", actionscriptType);
    }

    @Test
    public void testGetActionscriptNumberType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Integer.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Long.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Float.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Double.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Byte.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(float.class);
        assertEquals("Number", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(double.class);
        assertEquals("Number", actionscriptType);
    }

    @Test
    public void testGetActionscriptArrayCollectionType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(ArrayList.class);
        assertEquals("Array", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(List.class);
        assertEquals("Array", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Collection.class);
        assertEquals("Array", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Set.class);
        assertEquals("Array", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(HashSet.class);
        assertEquals("Array", actionscriptType);
    }

    @Test
    public void testGetActionscriptByteArrayType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Byte[].class);
        assertEquals("ByteArray", actionscriptType);
    }

    @Test
    public void testGetActionscriptOtherType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(Separator.class);
        assertEquals("Separator", actionscriptType);
    }

    @Test
    public void testGetActionscriptIntType() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(int.class);
        assertEquals("int", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(short.class);
        assertEquals("int", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(byte.class);
        assertEquals("int", actionscriptType);
        actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeName(byte[].class);
        assertEquals("int", actionscriptType);
    }

    @Test
    public void testGetActionscriptByteArrayTypeWithPackage() {
        String actionscriptType = JavaTypeAndActionscriptTypeMapping.getAsTypeNameWithPackage(Byte[].class);
        assertEquals("flash.utils.ByteArray", actionscriptType);
    }

}
