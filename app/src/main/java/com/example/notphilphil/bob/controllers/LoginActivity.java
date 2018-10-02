package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notphilphil.bob.R;

public class LoginActivity extends AppCompatActivity {
    private EditText login_et;
    private EditText password_et;

    private final String login = "user";
    private final String password = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.email_et);
        Button login_bt = findViewById(R.id.login_button);
        Button register_bt = findViewById(R.id.register_button);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPressed(v);
            }
        });
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                intent.putExtra("username", login_et.getText().toString());
                startActivity(intent);
            }
        });
    }

    protected void loginPressed(View view) {
        String curr_login = login_et.getText().toString();
        String curr_password = password_et.getText().toString();

        if (!curr_login.equals(login)) {
            login_et.setError("Login error! Bad username");
            return;
        }
        if (!curr_password.equals(password)) {
            password_et.setError("Login error! Bad password");
            return;
        }
        // Would normally put authentication here but the above if's are enough (M4)
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
