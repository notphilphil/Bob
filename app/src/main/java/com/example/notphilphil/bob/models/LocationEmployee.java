package com.example.notphilphil.bob.models;

public class LocationEmployee extends User {

    private int employeeID;
    private Location locationID;

    public LocationEmployee(String userID, String name, int _employeeID, Location _location) {
        super(userID, name);
        employeeID = _employeeID;
        locationID = _location;
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

    public Location getLocationID() {
        return locationID;
    }

    public String toString() {
        return "My employee ID is " + employeeID + " and my location ID is: " + locationID ;
    }
}
