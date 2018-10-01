package com.example.notphilphil.bob.models;

public class EmployeeType extends UserType {

    private int employeeID;
    private Location locationID;

    EmployeeType(int _employeeID, Location _location) {
        super();
        employeeID = _employeeID;
        locationID = _location;
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
