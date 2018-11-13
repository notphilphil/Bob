package com.example.notphilphil.bob.controllers;


import android.test.mock.MockContext;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginActivity {
    static File file;

    @BeforeAll
    public static void buildFile() {
        LoggedUser.setTesting(true);
        file = new File("regUsers.txt");
    }

    private void addUser(String name, String email, String pass, LoggedUser.PermissionsEnum usertype) {
        try {
            OutputStreamWriter outWriter =
                    new OutputStreamWriter(new FileOutputStream(file, true));
            String toWrite = name+","+email+","+pass+","+usertype+"\n";
            outWriter.write(toWrite);
            outWriter.close();
        } catch (IOException err) {

        }
    }

    @Test
    public void testLogin() {
        LoginActivity testClass = new LoginActivity();
        String user = "phil3";
        String pass = "lip3";
        assertFalse(testClass.loginPressed(user, pass, file, new MockContext()));
    }

    @Test
    public void testLoginUserExists() {
        LoginActivity testClass = new LoginActivity();
        String user = "phil";
        String email = "pass";
        String pass = "lip";
        LoggedUser.PermissionsEnum user_type = LoggedUser.PermissionsEnum.USER;
        addUser(user, email, pass, user_type);
        assertTrue(testClass.loginPressed(user, pass, file, new MockContext()));
    }
}
