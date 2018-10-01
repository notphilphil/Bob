package com.example.notphilphil.bob.models;

import android.location.Location;

public class EmployeeType extends UserType {

    private int employeeID;
    private Location locationID;

    public void EmployeeType(int _employeeID, Location _locationID) {
        employeeID = _employeeID;
        locationID = _locationID;

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
