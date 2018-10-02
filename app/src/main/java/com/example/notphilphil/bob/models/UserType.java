package com.example.notphilphil.bob.models;

import java.util.Arrays;
import java.util.List;

public class UserType {

    public static List<String> legalUserTypes = Arrays.asList("User", "Admin", "Manager", "Location Employee");

    private String userID ;
    private String name ;

    public UserType() {
        userID = "jDoe3" ;
        name = "John Doe" ;
    }

    public UserType(String id, String aName) {
        userID = id ;
        name = aName ;

    }

    public static int findPosition(String code) {
        int i = 0;
        while (i < legalUserTypes.size()) {
            if (code.equals(legalUserTypes.get(i))) return i;
            ++i;
        }
        return 0;
    }

    public String getName() {
        return name ;
    }

    public String getID() {
        return userID ;
    }


    public String toString() {
        return "My name is " + name + " and my user ID is: " + userID ;
    }

}
