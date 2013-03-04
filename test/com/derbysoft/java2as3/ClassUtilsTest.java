package com.derbysoft.java2as3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClassUtilsTest {

    @Test
    public void testIsBasicJavaType() {
        assertTrue(ClassUtils.isBasicJavaType(Class.class));
        assertTrue(ClassUtils.isBasicJavaType(String.class));
        assertTrue(ClassUtils.isBasicJavaType(Date.class));
        assertTrue(ClassUtils.isBasicJavaType(java.sql.Date.class));
        assertTrue(ClassUtils.isBasicJavaType(BigDecimal.class));
        assertTrue(ClassUtils.isBasicJavaType(List.class));
        assertTrue(ClassUtils.isBasicJavaType(int.class));
        assertFalse(ClassUtils.isBasicJavaType(AS3Writer.class));
    }
}
