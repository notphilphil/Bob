package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notphilphil.bob.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logout_bt = findViewById(R.id.logout_bt);
        Button location_bt = findViewById(R.id.location_bt);
        Button map_bt = findViewById(R.id.map_bt);

        map_bt.setOnClickListener(v -> {
            Intent intent = new Intent(this, Maps.class);
            startActivity(intent);
            finish();
        });


        logout_bt.setOnClickListener(v -> {
            LoggedUser.logOut();
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        location_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LocationsActivity.class);
            startActivity(intent);
        });
    }
}
