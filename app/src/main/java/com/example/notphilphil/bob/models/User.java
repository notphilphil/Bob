package com.example.notphilphil.bob.models;

public class User {
    private final String id;
    private final String name;

    public User(String aId, String aName) {
        id = aId;
        name = aName;
    }

    User() {
        this("jDoe3", "John Doe");
    }

    public String getName() {
        return name ;
    }

    public String getID() {
        return id ;
    }

    public String toString() {
        return "My name is " + name + " and my user ID is: " + id ;
    }

}
