package com.example.notphilphil.bob.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Admin
import com.example.notphilphil.bob.models.LocationEmployee
import com.example.notphilphil.bob.models.Manager
import com.example.notphilphil.bob.models.User

import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.OutputStreamWriter

class RegisterActivity : AppCompatActivity() {

    private var userSpinner: Spinner? = null
    private var un_et: EditText? = null
    private var em_et: EditText? = null
    private var pw_et: EditText? = null
    private var unError: String? = null
    private var emError: String? = null
    private var pwError: String? = null
    private var regUsers: File? = null
    var parts: Array<String>? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val back_to_login_bt = findViewById<Button>(R.id.login_button)
        val register_bt = findViewById<Button>(R.id.register_button)
        userSpinner = findViewById(R.id.userspinner)
        un_et = findViewById(R.id.username_et)
        em_et = findViewById(R.id.email_et)
        pw_et = findViewById(R.id.password_et)

        back_to_login_bt.setOnClickListener { v ->
            val intent = Intent(v.context, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val classAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                LoggedUser.legalUserTypes)
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userSpinner!!.adapter = classAdapter

        register_bt.setOnClickListener { v ->
            unError = ""
            emError = ""
            pwError = ""

            val username = un_et!!.text.toString()
            val email = em_et!!.text.toString()
            val password = pw_et!!.text.toString()
            val usertype = userSpinner!!.selectedItem.toString()
                    .toUpperCase().replace(" ", "_")

            try {
                if (registerUser(username, email, password, usertype)) {
                    val intent = Intent(v.context, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (!unError!!.isEmpty()) un_et!!.error = unError
                    if (!emError!!.isEmpty()) em_et!!.error = emError
                    if (!pwError!!.isEmpty()) pw_et!!.error = pwError
                }
            } catch (err: Exception) {
                Log.w("Registration",
                        "Registration failed with error => " + err.message)
            }
        }
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
    @Throws(IOException::class)
    internal fun registerUser(un: String, em: String, pw: String, ut: String): Boolean {
        if (un.isEmpty() || em.isEmpty() || pw.isEmpty()) {
            return false
        }
        var found_duplicate = false

        regUsers = File(applicationContext.filesDir, "regUsers.txt")

        val outWriter = OutputStreamWriter(FileOutputStream(regUsers!!, true))
        val toWrite = "$un,$em,$pw,$ut\n"
        val br = BufferedReader(FileReader(regUsers!!))
        var line = br.readLine()
        while ((line) != null) {
            parts = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            // Might want to check for used username as well
            if (em == parts!![1]) {
                if (un == parts!![0] &&
                        pw == parts!![2] &&
                        ut == parts!![3]) {
                    //("Registration", "Account already exists");
                    val text = "Account already exists, logging with credentials"
                    val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
                    toast.show()
                    found_duplicate = true
                } else {
                    // //("Registration", "Email in use");
                    emError = "Invalid: Email already in use"
                    return false
                }
            }
            line = br.readLine()
        }
        if (!found_duplicate) outWriter.append(toWrite)
        outWriter.close()

        when (LoggedUser.PermissionsEnum.valueOf(ut)) {
            LoggedUser.PermissionsEnum.NONE -> return false
            LoggedUser.PermissionsEnum.USER -> LoggedUser.newInstance(User(em, un))
            LoggedUser.PermissionsEnum.LOCATION_EMPLOYEE -> LoggedUser.newInstance(LocationEmployee(em, un))
            LoggedUser.PermissionsEnum.MANAGER -> LoggedUser.newInstance(Manager())
            LoggedUser.PermissionsEnum.ADMIN -> LoggedUser.newInstance(Admin())
            else -> return false
        }
        //("Registration", "Registration successful");
        return true
    }

    fun setRegUsers(regUsers: File) {
        this.regUsers = regUsers
    }

    companion object {
        private var testing = false
        fun setTesting(newTesting: Boolean) {
            testing = newTesting
        }
    }
}