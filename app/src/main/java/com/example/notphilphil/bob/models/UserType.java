package com.example.notphilphil.bob.models;

public class UserType {

    private String userID ;
    private String name ;


    public void UserType() {
        userID = "jDoe3" ;
        name = "John Doe" ;
    }


    public void UserType(String id, String aName) {
        userID = id ;
        name = aName ;

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
