package com.example.notphilphil.bob.models;

import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;

public class Item implements Serializable {
    private String type;
    private String id;
    private String color;
    private double price;

    private String key;

    private static String[] tokens = {"type", "id", "color", "price"};

    public Item() {
        this("type", "id", "color", 0, "key");
    }

    public Item(String type, String id, String color, double price, String key) {
        this.type = type;
        this.id = id;
        this.color = color;
        this.price = price;
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addValue(String key, String data) {
        switch (Arrays.asList(tokens).indexOf(key)) {
            case 0: this.setType(data); break;
            case 1: this.setId(data); break;
            case 2: this.setColor(data); break;
            case 3: this.setPrice(Double.parseDouble(data)); break;
        }
    }

    @Override
    public String toString() {
        return "A beautiful "+this.color+" "+this.type+" at the low price of "+this.price;
    }
}
