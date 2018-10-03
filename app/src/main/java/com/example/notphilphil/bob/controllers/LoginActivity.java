package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Employee;
import com.example.notphilphil.bob.models.User;

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

        login_bt.setOnClickListener(this::loginPressed);

        register_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            intent.putExtra("username", login_et.getText().toString());
            startActivity(intent);
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
        Class<?> loggedUserType;
        // Need to store user type in app file
        switch ((int) (Math.random() * 4)) {
            case 0:
                loggedUserType = User.class;
                break;
            case 1:
                loggedUserType = Employee.class;
                break;
            case 2:
                loggedUserType = User.class;
                break;
            case 3:
                loggedUserType = Employee.class;
                break;
            default: return;
        }
        try {
            LoggedUser.newInstance(new User("12345", "Bob"));
        } catch (Exception err) {
            Log.d("Login", err.getMessage());
        }

        LoggedUser.logOut();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
