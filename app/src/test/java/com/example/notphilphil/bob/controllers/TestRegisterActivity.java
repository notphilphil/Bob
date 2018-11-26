package com.example.notphilphil.bob.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tester for the registerUsers() method in RegisterActivity
 */
class TestRegisterActivity {
    private static File regUsers;
    private static final RegisterActivity test = new RegisterActivity();
    private final String[] testUser = {"Bob", "bob@email.com", "bob", "USER"};

    @BeforeAll
    static void buildFile() {
        RegisterActivity.setTesting(true);
        regUsers = new File("regUsers.txt");
    }

    @BeforeAll
    static void makeRegisterActivity() throws IOException {
        test.setRegUsers(regUsers);
        test.registerUser("Bob", "bob@email.com", "bob", "USER");
    }

    @Test
    void registerUserName() throws IOException {
        assertEquals(testUser[0], test.getParts()[0]);
    }

    @Test
    void registerEmail() throws IOException {
        assertEquals(testUser[1], test.getParts()[1]);
    }

    @Test
    void registerPassword() throws IOException {
        assertEquals(testUser[2], test.getParts()[2]);
    }

    @Test
    void registerUserType() throws IOException {
        assertEquals(testUser[3].toLowerCase(), test.getParts()[3].toLowerCase());
    }
}
