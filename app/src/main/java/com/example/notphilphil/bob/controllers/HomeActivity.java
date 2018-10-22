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

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseApp.initializeApp(this);

        Button logout_bt = findViewById(R.id.logout_bt);
        Button location_bt = findViewById(R.id.location_bt);

        logout_bt.setOnClickListener(v -> {
            LoggedUser.logOut();
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        location_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LocationsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
