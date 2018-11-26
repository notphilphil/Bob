package com.example.notphilphil.bob.models;

import java.util.ArrayList;
import java.util.List;

class Inventory {
    //    private final String key;

    private Inventory(List<Item> items) {
        //        this.key = this.key;
    }

    public Inventory() {
        this(new ArrayList<>());
    }

}
