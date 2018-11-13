package com.example.notphilphil.bob.controllers;

import android.test.mock.MockContext;

import com.example.notphilphil.bob.models.User;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TestLoggedUser {
    @BeforeAll
    public void init() {
        LoggedUser.setTesting(true);
    }

    @Test
    public void testLogout() {
        LoggedUser.setTesting(true);
        String id = "id";
        String name = "name";
        LoggedUser.newInstance(new User(id, name), new MockContext());

        // Check if logged in
        assertEquals(id, LoggedUser.getId());
        assertEquals(name, LoggedUser.getName());
        assertEquals(LoggedUser.PermissionsEnum.USER, LoggedUser.getPermissions());
        assertTrue(LoggedUser.isLoggedIn());

        LoggedUser.logOut();

        // Check if logged out
        assertEquals("", LoggedUser.getId());
        assertEquals("", LoggedUser.getName());
        assertEquals(LoggedUser.PermissionsEnum.NONE, LoggedUser.getPermissions());
        assertFalse(LoggedUser.isLoggedIn());
    }
}
