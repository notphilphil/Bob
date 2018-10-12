package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notphilphil.bob.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView welcome_tv = findViewById(R.id.welcome_tv);
        Button logout_bt = findViewById(R.id.logout_bt);

        logout_bt.setOnClickListener(v -> {
            LoggedUser.logOut();
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        String newT = "Welcome, " + LoggedUser.getName();
        welcome_tv.setText(newT);
    }
}
