package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.notphilphil.bob.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    private Spinner userSpinner;
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

        userSpinner = (Spinner) findViewById(R.id.userspinner);

        ArrayAdapter<String> classAdapter = new ArrayAdapter (this,android.R.layout.simple_spinner_item, LoggedUser.legalUserTypes);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(classAdapter);

        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username_et)).getText().toString();
                String email = ((EditText) findViewById(R.id.email_et)).getText().toString();
                String password = ((EditText) findViewById(R.id.email_et)).getText().toString();
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
    private boolean registerUser(String un, String em, String pw /* User ut */) throws IOException {
        if (un.isEmpty() || em.isEmpty() || pw.isEmpty()) {
            return false;
        }
        File dir = getApplicationContext().getFilesDir();
        File regUsers = new File(dir, "regUsers.txt");
        FileOutputStream fOut = new FileOutputStream(regUsers);
        OutputStreamWriter outWriter = new OutputStreamWriter(fOut);
        String toWrite = un+","+em+","+pw;
        if (regUsers.exists()) {
            // Does exist, just write to file
            BufferedReader br = new BufferedReader(new FileReader(regUsers));
            String line;
            Log.d("Registration", "File already includes: ");
            while ((line = br.readLine()) != null) {
                Log.d("Registration", line+"\n");
            }
            toWrite = "\n" + toWrite;
            outWriter.append(toWrite);
            br.close();
        } else {
            // Doesn't exist, create new file
            Log.d("Registration", "File doesn't exist, first registration");
            if (regUsers.createNewFile()) {
                outWriter.write(toWrite);
            }
        }
        outWriter.close();
        fOut.close();
        return true;
    }
}