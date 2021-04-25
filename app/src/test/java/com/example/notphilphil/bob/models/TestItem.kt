package com.example.notphilphil.bob.models

import org.junit.jupiter.api.Test

import junit.framework.Assert.assertEquals


internal class TestItem {
    @Test
    fun testAdd() {
        //register
        val testing = Item()

        val sampleTokens = arrayOf("typeTest", "idTest", "colorTest", "1.0", "categoryTest")
        val sampleKey = "abcdefghijklmnopqrstuvwxyz123567890"

        //Test addValue - Type, Case 0
        testing.addValue(Item.tokens[0], sampleTokens[0])
        assertEquals(sampleTokens[0], testing.type)

        //Test addValue - Id, Case 1
        testing.addValue(Item.tokens[1], sampleTokens[1])
        assertEquals(sampleTokens[1], testing.id)

        //Test addValue - Color, Case 2
        testing.addValue(Item.tokens[2], sampleTokens[2])
        assertEquals(sampleTokens[2], testing.color)

        //Test addValue - Price, Case 3
        testing.addValue(Item.tokens[3], sampleTokens[3])
        assertEquals(sampleTokens[3], java.lang.Double.toString(testing.price))

        //Test addValue - Category, Case 4
        testing.addValue(Item.tokens[4], sampleTokens[4])
        assertEquals(sampleTokens[4], testing.category)

        //Test setKey
        testing.key = sampleKey
        assertEquals(testing.key, sampleKey)

        //Test addValue - Type, Default
        val toCompare = testing.toString()
        testing.addValue("This is Default", "This is Default")
        assertEquals(testing.toString(), toCompare)


        val str = "Junit is working fine"
        assertEquals("Junit is working fine", str)
    }
}