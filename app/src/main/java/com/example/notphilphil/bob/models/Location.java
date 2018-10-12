package com.example.notphilphil.bob.models;

import com.example.notphilphil.bob.errors.InvalidLocationException;

public class Location {
    private double latitude;
    private double longitude;
    private String destination;

    public Location(double lat, double lon, String dest) {
        latitude = lat;
        longitude = lon;
        destination = dest;
    }

    public Location() {
        this(0, 0, "Empty Location");
    }

    /**
     * Set new latitude and longitude for this Location object
     *
     * @param loc New location in array form [latitude, longitude]
     * @throws InvalidLocationException
     */
    public void setLocation(double[] loc) throws InvalidLocationException {
        if (loc.length != 2) {
            throw new InvalidLocationException("New location must be length 2");
        }
        latitude = loc[0];
        longitude = loc[1];
    }

    /**
     * Set the new destination for this Location object
     *
     * @param dest New location destination
     */
    public void setDestination(String dest) {
        destination = dest;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDestination() {
        return destination;
    }

    public String toString() {
        return destination + " at [" + latitude + ", " + longitude + "]";
    }
}
