package com.example.notphilphil.bob.models;

import com.example.notphilphil.bob.errors.InvalidLocationException;

public class Location {
    private String name;
    private String type;
    private double latitude;
    private double longitude;
    private String address;
    private String phone;

    public Location(String name, String type, double latitude, double longitude, String address, String phone) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
    }

    public Location() {
        this("Test", "Test", 0, 0, "123 Baker St.", "123-456-7890");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return name + " at [" + latitude + ", " + longitude + "]";
    }
}
