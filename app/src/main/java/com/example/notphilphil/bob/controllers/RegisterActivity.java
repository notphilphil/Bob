package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.notphilphil.bob.R;
import com.example.notphilphil.bob.models.Admin;
import com.example.notphilphil.bob.models.LocationEmployee;
import com.example.notphilphil.bob.models.Manager;
import com.example.notphilphil.bob.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    private Spinner userSpinner;
    private EditText un_et;
    private EditText em_et;
    private EditText pw_et;
    private String unError;
    private String emError;
    private String pwError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button back_to_login_bt = findViewById(R.id.login_button);
        Button register_bt = findViewById(R.id.register_button);
        userSpinner = findViewById(R.id.userspinner);
        un_et = findViewById(R.id.username_et);
        em_et = findViewById(R.id.email_et);
        pw_et = findViewById(R.id.password_et);

        back_to_login_bt.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        ArrayAdapter<String> classAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        LoggedUser.legalUserTypes);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(classAdapter);

        register_bt.setOnClickListener(v -> {
            unError = "";
            emError = "";
            pwError = "";

            String username = un_et.getText().toString();
            String email = em_et.getText().toString();
            String password = pw_et.getText().toString();
            String usertype = userSpinner.getSelectedItem().toString()
                    .toUpperCase().replace(" ", "_");

            try {
                if (registerUser(username, email, password, usertype)) {
                    Intent intent = new Intent(v.getContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (!unError.isEmpty()) un_et.setError(unError);
                    if (!emError.isEmpty()) em_et.setError(emError);
                    if (!pwError.isEmpty()) pw_et.setError(pwError);
                }
            } catch (Exception err) {
                Log.w("Registration",
                        "Registration failed with error => "+err.getMessage());
            }
        });
    }

    /**
     * Tries to register a user given the four needed inputs.
     *
     * @param un username as a string
     * @param em email as a string
     * @param pw password as a string
     * @param ut user type as a string
     * @return boolean reflecting whether or not the user was successfully registered
     */
    private boolean registerUser(String un, String em, String pw, String ut) throws IOException {
        if (un.isEmpty() || em.isEmpty() || pw.isEmpty()) {
            return false;
        }
        boolean found_duplicate = false;
        File regUsers = new File(getApplicationContext().getFilesDir(), "regUsers.txt");
        OutputStreamWriter outWriter =
                new OutputStreamWriter(new FileOutputStream(regUsers, true));
        String toWrite = un+","+em+","+pw+","+ut+"\n";
        BufferedReader br = new BufferedReader(new FileReader(regUsers));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            // Might want to check for used username as well
            if (em.equals(parts[1])) {
                if (un.equals(parts[0]) &&
                    pw.equals(parts[2]) &&
                    ut.equals(parts[3])) {
                    Log.d("Registration", "Account already exists");
                    String text = "Account already exists, logging with credentials";
                    Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
                    toast.show();
                    found_duplicate = true;
                } else {
                    Log.d("Registration", "Email in use");
                    emError = "Invalid: Email already in use";
                    return false;
                }
            }
        }
        if (!found_duplicate) outWriter.append(toWrite);
        outWriter.close();

        switch (LoggedUser.PermissionsEnum.valueOf(ut)) {
            case NONE: return false;
            case USER: LoggedUser.newInstance(new User(em, un), getBaseContext()); break;
            case LOCATION_EMPLOYEE: LoggedUser.newInstance(new LocationEmployee(em, un), getBaseContext()); break;
            case MANAGER: LoggedUser.newInstance(new Manager(), getBaseContext()); break;
            case ADMIN: LoggedUser.newInstance(new Admin(), getBaseContext()); break;
            default: return false;
        }
        Log.d("Registration", "Registration successful");
        return true;
    }
}