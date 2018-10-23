package com.example.notphilphil.bob.models;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private String key;

    public Inventory(ArrayList<Item> items, String key) {
        this.items = items;
        this.key = key;
    }

    public Inventory() {
        this(new ArrayList<>(), "key");
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
