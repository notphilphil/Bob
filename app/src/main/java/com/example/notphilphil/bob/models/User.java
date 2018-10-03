package com.example.notphilphil.bob.models;

public class User {
    private String userID;
    private String name;

    public User() {
        userID = "jDoe3" ;
        name = "John Doe" ;
    }

    public User(String id, String aName) {
        userID = id ;
        name = aName ;
    }

    public String getName() {
        return name ;
    }

    public String getUserID() {
        return userID ;
    }

    public String toString() {
        return "My name is " + name + " and my user ID is: " + userID ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
