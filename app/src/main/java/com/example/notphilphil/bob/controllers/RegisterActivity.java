package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.UserType;

public class RegisterActivity extends AppCompatActivity {

    private Spinner userSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button back_to_login_bt = findViewById(R.id.login_button);
        back_to_login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        userSpinner = (Spinner) findViewById(R.id.userspinner);

        ArrayAdapter<String> classAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, UserType.legalUserTypes);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(classAdapter);
    }
}
