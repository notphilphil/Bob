package com.example.notphilphil.bob.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Admin
import com.example.notphilphil.bob.models.LocationEmployee
import com.example.notphilphil.bob.models.Manager
import com.example.notphilphil.bob.models.User
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var login_et: EditText
    private lateinit var password_et: EditText

    //for facebook
    internal lateinit var loginButton: LoginButton
    internal var textView: TextView? = null
    internal lateinit var callbackManager: CallbackManager
    //end of facebook items


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext) //initialize facebook SDK
        setContentView(R.layout.activity_login)

        login_et = this.findViewById(R.id.username_et)
        password_et = findViewById(R.id.email_et)
        val login_bt = findViewById<Button>(R.id.login_button)
        val register_bt = findViewById<Button>(R.id.register_button)
        val guest_bt = findViewById<Button>(R.id.guestButton)


        login_bt.setOnClickListener { v ->
            val curr_login = login_et!!.text.toString()
            val curr_password = password_et!!.text.toString()
            val dir = applicationContext.filesDir
            val regUsers = File(dir, "regUsers.txt")
            loginPressed(curr_login, curr_password, regUsers, this)
        }

        register_bt.setOnClickListener { v ->
            val intent = Intent(v.context, RegisterActivity::class.java)
            intent.putExtra("username", login_et!!.text.toString())
            startActivity(intent)
        }

        guest_bt.setOnClickListener { v ->
            val intent = Intent(v.context, HomeActivity::class.java)
            LoggedUser.newInstance()
            startActivity(intent)
        }


        /////////// start of facebook

        loginButton = findViewById<Button>(R.id.fblogin_button) as LoginButton  //facebook
        textView = findViewById<TextView>(R.id.textView)  //facebook
        callbackManager = CallbackManager.Factory.create() //facebook
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> { //facebook
            override fun onSuccess(loginResult: LoginResult) {
                val intent = Intent(loginButton.context, HomeActivity::class.java)
                LoggedUser.newInstance()
                startActivity(intent)
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }
        })

        //////////end of facebook inside the onCreate() method
    }

    //////method created for facebook activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
    /////////// end of facebook method


    fun loginPressed(curr_login: String, curr_password: String, regUsers: File, context: Context): Boolean {
        if (regUsers.exists()) {
            try {
                val bReader = BufferedReader(FileReader(regUsers))
                var line = bReader.readLine()
                while ((line) != null) {
                    val parts = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    if (curr_login == parts[0] && curr_password == parts[2]) {
                        when (LoggedUser.PermissionsEnum.valueOf(parts[3])) {
                            LoggedUser.PermissionsEnum.NONE -> {
                            }
                            LoggedUser.PermissionsEnum.USER -> LoggedUser.newInstance(User(parts[1], parts[0]))
                            LoggedUser.PermissionsEnum.LOCATION_EMPLOYEE -> LoggedUser.newInstance(LocationEmployee(parts[1], parts[0]))
                            LoggedUser.PermissionsEnum.MANAGER -> LoggedUser.newInstance(Manager())
                            LoggedUser.PermissionsEnum.ADMIN -> LoggedUser.newInstance(Admin())
                            else -> {
                            }
                        }
                        //if (!LoggedUser.getTesting()) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        //}
                        return true
                    }
                    line = bReader.readLine()
                }

                //                Log.d("Login", "Failed to validate login");
                return false
            } catch (err: IOException) {
                Log.d("Login", "Got an error! " + err.message)
            }

        }
        return false
    }
}
