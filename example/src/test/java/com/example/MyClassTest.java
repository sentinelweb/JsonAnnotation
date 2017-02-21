package com.example;

import org.junit.Before;
import org.junit.Test;

import uk.co.sentinelweb.jsonannotation.JsonResource;
import uk.co.sentinelweb.jsonannotation.JsonResourceInitialiser;

import static org.junit.Assert.*;

/**
 * Created by robertm on 21/02/2017.
 */
public class MyClassTest {

    @JsonResource(fileName = "jr.json")
    public MyClass test;

    @Before
    public void setUp() throws Exception {
        JsonResourceInitialiser.initAnnotations(this);
    }

    @Test
    public void test() {
        assertEquals(test.var1, "var1value");
        assertEquals(test.var2, "var2value");
    }

}