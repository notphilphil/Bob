package com.example.notphilphil.bob.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class TestItem {
    @Test
    public void testAdd() {
        //register
        Item testing = new Item();

        String[] sampleTokens = {"typeTest", "idTest", "colorTest", "1.0", "categoryTest"};

        //Test addValue - Type, Case 0
        testing.addValue(Item.tokens[0], sampleTokens[0]);
        assertEquals(sampleTokens[0], testing.getType());

        //Test addValue - Id, Case 1
        testing.addValue(Item.tokens[1], sampleTokens[1]);
        assertEquals(sampleTokens[1], testing.getId());

        //Test addValue - Color, Case 2
        testing.addValue(Item.tokens[2], sampleTokens[2]);
        assertEquals(sampleTokens[2], testing.getColor());

        //Test addValue - Price, Case 3
        testing.addValue(Item.tokens[3], sampleTokens[3]);
        assertEquals(sampleTokens[3], Double.toString(testing.getPrice()));

        //Test addValue - Category, Case 4
        testing.addValue(Item.tokens[4], sampleTokens[4]);
        assertEquals(sampleTokens[4], testing.getCategory());

        //Test addValue - Type, Default
        String toCompare = testing.toString();
        testing.addValue("This is Default", "This is Default");
        assertEquals(testing.toString(), toCompare);


        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }
}