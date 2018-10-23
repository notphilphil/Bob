package com.example.notphilphil.bob.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        populatePage(this);

        findViewById(R.id.return_btn).setOnClickListener(v -> this.onBackPressed());
    }

    private void populatePage(Context context) {
        String itemKey = getIntent().getStringExtra("itemKey");
        String inventoryKey = getIntent().getStringExtra("inventoryKey");
        DatabaseReference ref = LoggedUser.getRef().child("inventories").child(inventoryKey).child(itemKey);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Button edit_btn = findViewById(R.id.edit_btn);
                Button delete_btn = findViewById(R.id.delete_btn);

                if (dataSnapshot.exists()) {
                    TextView type_tv = findViewById(R.id.type_tv);
                    TextView color_tv = findViewById(R.id.color_tv);
                    TextView id_tv = findViewById(R.id.id_tv);
                    TextView price_tv = findViewById(R.id.price_tv);
                    Item item = new Item();
                    for (DataSnapshot val : dataSnapshot.getChildren()) {
                        item.addValue(val.getKey(), val.getValue().toString());
                    }
                    type_tv.setText(dataSnapshot.child("type").getValue().toString());
                    color_tv.setText(dataSnapshot.child("color").getValue().toString());
                    id_tv.setText(dataSnapshot.child("id").getValue().toString());
                    price_tv.setText(dataSnapshot.child("price").getValue().toString());

                    edit_btn.setOnClickListener(v -> {
                        Intent intent = new Intent(context, ModifyItemActivity.class);
                        intent.putExtra("item", item);
                        intent.putExtra("itemKey", itemKey);
                        intent.putExtra("inventoryKey", inventoryKey);
                        intent.putExtra("edit", true);
                        startActivity(intent);
                    });
                }

                delete_btn.setOnClickListener(v -> {
                    onBackPressed();
                    ref.removeValue();
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
