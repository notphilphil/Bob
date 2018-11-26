package com.example.notphilphil.bob.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Admin;
import com.example.notphilphil.bob.models.LocationEmployee;
import com.example.notphilphil.bob.models.Manager;
import com.example.notphilphil.bob.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private EditText login_et;
    private EditText password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.email_et);
        Button login_bt = findViewById(R.id.login_button);
        Button register_bt = findViewById(R.id.register_button);


        login_bt.setOnClickListener(v -> {
            String curr_login = login_et.getText().toString();
            String curr_password = password_et.getText().toString();
            File dir = getApplicationContext().getFilesDir();
            File regUsers = new File(dir, "regUsers.txt");
            loginPressed(curr_login, curr_password, regUsers, this);
        });

        register_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            intent.putExtra("username", login_et.getText().toString());
            startActivity(intent);
        });
    }

    boolean loginPressed(String curr_login, String curr_password, File regUsers, Context context) {
        if (regUsers.exists()) {
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(regUsers));
                String line;
                while ((line = bReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (curr_login.equals(parts[0]) && curr_password.equals(parts[2])) {
                        switch (LoggedUser.PermissionsEnum.valueOf(parts[3])) {
                            case NONE: break;
                            case USER: LoggedUser.newInstance(new User(parts[1], parts[0])); break;
                            case LOCATION_EMPLOYEE: LoggedUser.newInstance(new LocationEmployee(parts[1], parts[0])); break;
                            case MANAGER: LoggedUser.newInstance(new Manager()); break;
                            case ADMIN: LoggedUser.newInstance(new Admin()); break;
                            default: break;
                        }
                        if (!LoggedUser.getTesting()) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        return true;
                    }

                }

//                Log.d("Login", "Failed to validate login");
                return false;
            } catch (IOException err) {
                Log.d("Login", "Got an error! " + err.getMessage());
            }
        }
        return false;
    }
}
