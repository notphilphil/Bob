package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Item;

import java.util.Objects;

public class ModifyItemActivity extends AppCompatActivity {
    EditText type_et;
    EditText color_et;
    EditText id_et;
    EditText price_et;
    EditText category_et;
    boolean edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_item);

        type_et = findViewById(R.id.type_et);
        color_et = findViewById(R.id.color_et);
        id_et = findViewById(R.id.id_et);
        price_et = findViewById(R.id.price_et);
        category_et = findViewById(R.id.category_et);
        edit = this.getIntent().getBooleanExtra("edit", false);

        if (edit) populatePage((Item) this.getIntent().getSerializableExtra("item"));

        FloatingActionButton save_btn = findViewById(R.id.save_btn);
        FloatingActionButton trash_btn = findViewById(R.id.trash_btn);

        trash_btn.setOnClickListener(v -> this.onBackPressed());
        save_btn.setOnClickListener(v -> this.onSave(getIntent()));
    }

    Item onSave(Intent intent) {
        String key;
        String invKey;
        Item item = new Item();
        item.setType(type_et.getText().toString());
        item.setId(id_et.getText().toString());
        item.setColor(color_et.getText().toString());
        item.setPrice(Double.parseDouble(price_et.getText().toString()));
        item.setCategory(category_et.getText().toString());
        if (!LoggedUser.getTesting()) {
            invKey = intent.getStringExtra("inventoryKey");
            if (edit) key = intent.getStringExtra("itemKey");
            else key = LoggedUser.getRef().child(intent.getStringExtra("inventoryKey")).push().getKey();
            item.setKey(key);
            LoggedUser.getRef().child("inventories").child(invKey).child(Objects.requireNonNull(key)).setValue(item);
            finish();
            return null;
        }
        else return item;
    }

    void populatePage(Item item) {
        type_et.setText(item.getType());
        color_et.setText(item.getColor());
        id_et.setText(item.getId());
        price_et.setText(String.valueOf(item.getPrice()));
        category_et.setText(item.getCategory());
    }
}
