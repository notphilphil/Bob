package com.example.notphilphil.bob.models

import org.junit.Test
import org.junit.Assert.*


class TestLocation {

    @Test
    fun addLatitude() {
        testing.addValue(Location.tokens[0], testTokens[0])
        assertEquals(testTokens[0], java.lang.Double.toString(testing.latitude))
    }

    @Test
    fun addLongitude() {
        testing.addValue(Location.tokens[1], testTokens[1])
        assertEquals(testTokens[1], java.lang.Double.toString(testing.longitude))
    }

    @Test
    fun addStreetAddress() {
        testing.addValue(Location.tokens[2], testTokens[2])
        assertEquals(testTokens[2], testing.streetAddress)
    }

    @Test
    fun addCity() {
        testing.addValue(Location.tokens[3], testTokens[3])
        assertEquals(testTokens[3], testing.city)
    }

    @Test
    fun addState() {
        testing.addValue(Location.tokens[4], testTokens[4])
        assertEquals(testTokens[4], testing.state)
    }

    @Test
    fun addZip() {
        testing.addValue(Location.tokens[5], testTokens[5])
        assertEquals(testTokens[5], testing.zip)
    }

    @Test
    fun addType() {
        testing.addValue(Location.tokens[6], testTokens[6])
        assertEquals(testTokens[6], testing.type)
    }

    @Test
    fun addPhone() {
        testing.addValue(Location.tokens[7], testTokens[7])
        assertEquals(testTokens[7], testing.phone)
    }

    @Test
    fun addWebsite() {
        testing.addValue(Location.tokens[8], testTokens[8])
        assertEquals(testTokens[8], testing.website)
    }

    @Test
    fun addName() {
        testing.addValue(Location.tokens[9], testTokens[9])
        assertEquals(testTokens[9], testing.name)
    }

    companion object {

        private val testTokens = arrayOf("84.3963", "33.7756", "North Ave NW", "Atlanta", "GA", "30332", "School", "(404) 894-2000", "gatech.edu", "Georgia Tech")
        private val testing = Location()
    }
}

