package com.example.notphilphil.bob.models;

import java.util.Arrays;

public class Location {
    private String Zip;
    private String Type;
    private String Phone;
    private String State;
    private String StreetAddress;
    private String Website;
    private double Latitude;
    private double Longitude;
    private String City;
    private String Name;

    public static String[] tokens = {"Latitude", "Longitude", "Street Address", "City", "State", "Zip", "Type", "Phone", "Website", "Name"};
    private String Key;

    public Location(String zip, String type, String phone, String state, String streetAddress, String website, String latitude, String longitude, String city, String name, String key) {
        Zip = zip;
        Type = type;
        Phone = phone;
        State = state;
        StreetAddress = streetAddress;
        Website = website;
        Latitude = Double.parseDouble(latitude);
        Longitude = Double.parseDouble(longitude);
        City = city;
        Name = name;
        Key = key;
    }

    public Location() {
        this("zip", "type", "phone", "state", "streetAddress", "website", "0", "0", "city", "name", "key");
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public void addValue(String key, String data) {
        switch (Arrays.asList(tokens).indexOf(key)) {
            case 0: this.setLatitude(Double.parseDouble(data)); break;
            case 1: this.setLongitude(Double.parseDouble(data)); break;
            case 2: this.setStreetAddress(data); break;
            case 3: this.setCity(data); break;
            case 4: this.setState(data); break;
            case 5: this.setZip(data); break;
            case 6: this.setType(data); break;
            case 7: this.setPhone(data); break;
            case 8: this.setWebsite(data); break;
            case 9: this.setName(data); break;
        }
    }

    @Override
    public String toString() {
        return
            "Latitude: " + this.Latitude +
            "\nLongitude: " + this.Longitude+
            "\nStreet Address: " + this.StreetAddress +
            "\nCity: " + this.City +
            "\nState: " + this.State +
            "\nZip: " + this.Zip +
            "\nType: " + this.Type +
            "\nPhone: " + this.Phone +
            "\nWebsite: " + this.Website;
    }
}
