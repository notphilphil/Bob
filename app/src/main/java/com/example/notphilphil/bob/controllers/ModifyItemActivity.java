package com.example.notphilphil.bob.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Item;

public class ModifyItemActivity extends AppCompatActivity {
    private EditText type_et;
    private EditText color_et;
    private EditText id_et;
    private EditText price_et;
    private EditText category_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_item);

        type_et = findViewById(R.id.type_et);
        color_et = findViewById(R.id.color_et);
        id_et = findViewById(R.id.id_et);
        price_et = findViewById(R.id.price_et);
        category_et = findViewById(R.id.category_et);

        boolean edit = this.getIntent().getBooleanExtra("edit", false);

        if (edit) populatePage(this.getIntent());

        FloatingActionButton save_btn = findViewById(R.id.save_btn);
        FloatingActionButton trash_btn = findViewById(R.id.trash_btn);

        trash_btn.setOnClickListener(v -> this.onBackPressed());
        save_btn.setOnClickListener(v -> {
            Intent res = new Intent();
            String key;
            String invKey = getIntent().getStringExtra("inventoryKey");
            if (edit) key = getIntent().getStringExtra("itemKey");
            else key = LoggedUser.getRef().child(getIntent().getStringExtra("inventoryKey")).push().getKey();
            Item item = new Item(type_et.getText().toString(), id_et.getText().toString(), color_et.getText().toString(), Double.parseDouble(price_et.getText().toString()), key, category_et.getText().toString());
            LoggedUser.getRef().child("inventories").child(invKey).child(key).setValue(item);
            finish();
        });
    }

    private void populatePage(Intent intent) {
        Item item = (Item) intent.getSerializableExtra("item");

        type_et.setText(item.getType());
        color_et.setText(item.getColor());
        id_et.setText(item.getId());
        price_et.setText(String.valueOf(item.getPrice()));
        category_et.setText(item.getCategory());
    }
}
