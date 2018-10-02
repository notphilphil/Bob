package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notphilphil.bob.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button back_to_login_bt = findViewById(R.id.login_button);
        Button register_bt = findViewById(R.id.register_button);

        back_to_login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.login_et)).getText().toString();
                String email = ((EditText) findViewById(R.id.email_et)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_et)).getText().toString();
                // Get user type from the selector thing
                try {
                    if (registerUser(username, email, password /* include user type */)) {
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception err) {

                }
            }
        });
    }

    /**
     *
     * @param un username as a string
     * @param em email as a string
     * @param pw password as a string
     * @return boolean reflecting whether or not the user was successfully registered
     */
    private boolean registerUser(String un, String em, String pw /* UserType ut */) throws IOException {
        if (un.isEmpty() || em.isEmpty() || pw.isEmpty()) {
            return false;
        }
        File cacheDir = getApplicationContext().getCacheDir();
        File regUsers = new File(cacheDir, "regUsers.txt");
        FileOutputStream fOut = new FileOutputStream(regUsers);
        OutputStreamWriter outWriter = new OutputStreamWriter(fOut);
        String toWrite = un+","+em+","+pw;
        if (regUsers.exists()) {
            // Does exist, just write to file
            toWrite = "\n" + toWrite;
            outWriter.append(toWrite);
        } else {
            // Doesn't exist, create new file
            if (regUsers.createNewFile()) {
                outWriter.write(toWrite);
            }
        }
        outWriter.close();
        fOut.close();
        return true;
    }
}