package com.example.notphilphil.bob.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tester for the registerUsers() method in RegisterActivity
 */
public class TestRegisterActivity {
    static File regUsers;
    static RegisterActivity test = new RegisterActivity();
    String[] testUser = {"Bob", "bob@email.com", "bob", "USER"};

    @BeforeAll
    public static void buildFile() {
        RegisterActivity.setTesting(true);
        regUsers = new File("regUsers.txt");
    }

    @BeforeAll
    public static void makeRegisterActivity() throws IOException {
        test.setRegUsers(regUsers);
        test.registerUser("Bob", "bob@email.com", "bob", "USER");
    }

    @Test
    public void registerUserName() throws IOException {
        assertEquals(testUser[0], test.getParts()[0]);
    }

    @Test
    public void registerEmail() throws IOException {
        assertEquals(testUser[1], test.getParts()[1]);
    }

    @Test
    public void registerPassword() throws IOException {
        assertEquals(testUser[2], test.getParts()[2]);
    }

    @Test
    public void registerUserType() throws IOException {
        assertEquals(testUser[3].toLowerCase(), test.getParts()[3].toLowerCase());
    }
}
