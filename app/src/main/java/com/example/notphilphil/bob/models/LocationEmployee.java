package com.example.notphilphil.bob.models;

public class LocationEmployee extends User {

    private final int employeeID;
    private final Location location;

    private LocationEmployee(String userID, String name, Location _location) {
        super(userID, name);
        employeeID = 0;
        location = _location;
    }

    public LocationEmployee(String userID, String name) {
        this(userID, name, new Location());
    }

    public String toString() {
        return "My employee ID is " + employeeID + " and my location is: " + location ;
    }
}
