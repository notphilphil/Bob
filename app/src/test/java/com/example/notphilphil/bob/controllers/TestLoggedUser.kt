package com.example.notphilphil.bob.controllers

import com.example.notphilphil.bob.models.User
import com.google.firebase.FirebaseApp

import org.junit.Test

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue

class TestLoggedUser {

    @Test
    fun testLogout() {
        //LoggedUser.setTesting(true);
        val id = "id"
        val name = "name"
        LoggedUser.newInstance(User(id, name))

        // Check if logged in
        assertEquals(id, LoggedUser.id)
        assertEquals(name, LoggedUser.name)
        assertEquals(LoggedUser.PermissionsEnum.USER, LoggedUser.permissions)
        assertTrue(LoggedUser.isLoggedIn)

        LoggedUser.logOut()

        // Check if logged out
        assertEquals("", LoggedUser.id)
        assertEquals("", LoggedUser.name)
        assertEquals(LoggedUser.PermissionsEnum.NONE, LoggedUser.permissions)
        assertFalse(LoggedUser.isLoggedIn)
    }
}
