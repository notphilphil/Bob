package com.example.notphilphil.bob.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestLocation {

    public static final String[] testTokens = {"84.3963", "33.7756", "North Ave NW", "Atlanta", "GA", "30332", "School", "(404) 894-2000", "gatech.edu", "Georgia Tech"};
    public static final Location testing = new Location();

    @Test
    public void addLatitude() {
        testing.addValue(Location.tokens[0], testTokens[0]);
        assertEquals(testTokens[0], Double.toString(testing.getLatitude()));
    }

    @Test
    public void addLongitude() {
        testing.addValue(Location.tokens[1], testTokens[1]);
        assertEquals(testTokens[1], Double.toString(testing.getLongitude()));
    }

    @Test
    public void addStreetAddress() {
        testing.addValue(Location.tokens[2], testTokens[2]);
        assertEquals(testTokens[2], testing.getStreetAddress());
    }

    @Test
    public void addCity() {
        testing.addValue(Location.tokens[3], testTokens[3]);
        assertEquals(testTokens[3], testing.getCity());
    }

    @Test
    public void addState() {
        testing.addValue(Location.tokens[4], testTokens[4]);
        assertEquals(testTokens[4], testing.getState());
    }

    @Test
    public void addZip() {
        testing.addValue(Location.tokens[5], testTokens[5]);
        assertEquals(testTokens[5], testing.getZip());
    }

    @Test
    public void addType() {
        testing.addValue(Location.tokens[6], testTokens[6]);
        assertEquals(testTokens[6], testing.getType());
    }

    @Test
    public void addPhone() {
        testing.addValue(Location.tokens[7], testTokens[7]);
        assertEquals(testTokens[7], testing.getPhone());
    }

    @Test
    public void addWebsite() {
        testing.addValue(Location.tokens[8], testTokens[8]);
        assertEquals(testTokens[8], testing.getWebsite());
    }

    @Test
    public void addName() {
        testing.addValue(Location.tokens[9], testTokens[9]);
        assertEquals(testTokens[9], testing.getName());
    }
}