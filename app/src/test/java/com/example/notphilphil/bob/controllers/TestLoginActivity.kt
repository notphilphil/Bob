package com.example.notphilphil.bob.controllers


import android.test.mock.MockContext

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

internal class TestLoginActivity {

    private fun addUser(name: String, email: String, pass: String, usertype: LoggedUser.PermissionsEnum) {
        try {
            val outWriter = OutputStreamWriter(FileOutputStream(file!!, true))
            val toWrite = "$name,$email,$pass,$usertype\n"
            outWriter.write(toWrite)
            outWriter.close()
        } catch (err: IOException) {

        }

    }

    @Test
    fun testLogin() {
        val testClass = LoginActivity()
        val user = "phil3"
        val pass = "lip3"
        assertFalse(testClass.loginPressed(user, pass, file!!, MockContext()))
    }

    @Test
    fun testLoginUserExists() {
        val testClass = LoginActivity()
        val user = "phil"
        val email = "pass"
        val pass = "lip"
        val user_type = LoggedUser.PermissionsEnum.USER
        addUser(user, email, pass, user_type)
        assertTrue(testClass.loginPressed(user, pass, file!!, MockContext()))
    }

    companion object {
        private var file: File? = null

        @BeforeAll
        fun buildFile() {
            //LoggedUser.setTesting(true);
            file = File("regUsers.txt")
        }
    }
}
