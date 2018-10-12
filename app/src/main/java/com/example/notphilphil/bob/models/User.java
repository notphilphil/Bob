package com.example.notphilphil.bob.models;

public class User {
    private String id;
    private String name;

    public User(String aId, String aName) {
        id = aId;
        name = aName;
    }

    public User() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }
}
