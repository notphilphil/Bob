package com.example.notphilphil.bob.models;

public class LocationEmployee extends User {

    private int employeeID;
    private Location location;

    public LocationEmployee(String userID, String name, int _employeeID, Location _location) {
        super(userID, name);
        employeeID = _employeeID;
        location = _location;
    }

    public LocationEmployee(String userID, String name) {
        this(userID, name, 0, new Location());
    }

    public LocationEmployee() {
        this("Test ID", "Test Name");
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public Location getLocation() {
        return location;
    }

    public String toString() {
        return "My employee ID is " + employeeID + " and my location is: " + location ;
    }
}
