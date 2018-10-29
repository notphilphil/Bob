package com.example.notphilphil.bob.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private String key;

    public Inventory(List<Item> items, String key) {
        this.items = items;
        this.key = key;
    }

    public Inventory() {
        this(new ArrayList<>(), "key");
    }

    public List<Item> getItems() {
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
