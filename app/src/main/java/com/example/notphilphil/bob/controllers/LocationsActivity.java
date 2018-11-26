package com.example.notphilphil.bob.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class LocationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Button home_bt = findViewById(R.id.home_bt);
        ListView location_list = findViewById(R.id.location_list);

        handleAlerts(location_list, this);    // Handle alerts to initialize locations

        home_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleAlerts(ListView location_list, Context context) {
        DatabaseReference ref = LoggedUser.getRef().child("locations");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Location> locations = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();
                Iterable<DataSnapshot> locs = dataSnapshot.getChildren();
                for (DataSnapshot loc : locs) {
                    Location newLoc = new Location();
                    names.add(Objects.requireNonNull(loc.child("Name").getValue()).toString());
                    for (DataSnapshot val : loc.getChildren()) newLoc.addValue(val.getKey(), Objects.requireNonNull(val.getValue()).toString());
                    newLoc.setKey(loc.getKey());
                    locations.add(newLoc);
                }
                ArrayAdapter locationsAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, names);
                location_list.setAdapter(locationsAdapter);
                location_list.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(parent.getContext(), LocationDetailsActivity.class);
                    intent.putExtra("key", locations.get(position).getKey());
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Probably do nothing.
                Log.w("LocationsActivity", databaseError.getDetails());
            }
        });
    }
}