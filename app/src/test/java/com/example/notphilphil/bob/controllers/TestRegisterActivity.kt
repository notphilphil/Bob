package com.example.notphilphil.bob.controllers

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.io.File
import java.io.IOException

import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Tester for the registerUsers() method in RegisterActivity
 */
internal class TestRegisterActivity {
    private val testUser = arrayOf("Bob", "bob@email.com", "bob", "USER")

    @Test
    @Throws(IOException::class)
    fun registerUserName() {
        assertEquals(testUser[0], test.parts!![0])
    }

    @Test
    @Throws(IOException::class)
    fun registerEmail() {
        assertEquals(testUser[1], test.parts!![1])
    }

    @Test
    @Throws(IOException::class)
    fun registerPassword() {
        assertEquals(testUser[2], test.parts!![2])
    }

    @Test
    @Throws(IOException::class)
    fun registerUserType() {
        assertEquals(testUser[3].toLowerCase(), test.parts!![3].toLowerCase())
    }

    companion object {
        private var regUsers: File? = null
        private val test = RegisterActivity()

        @BeforeAll
        fun buildFile() {
            RegisterActivity.setTesting(true)
            regUsers = File("regUsers.txt")
        }

        @BeforeAll
        @Throws(IOException::class)
        fun makeRegisterActivity() {
            test.setRegUsers(regUsers!!)
            test.registerUser("Bob", "bob@email.com", "bob", "USER")
        }
    }
}
