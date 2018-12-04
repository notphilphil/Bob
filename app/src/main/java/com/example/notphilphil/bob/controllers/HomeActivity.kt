package com.example.notphilphil.bob.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.example.notphilphil.bob.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logout_bt = findViewById<Button>(R.id.logout_bt)
        val location_bt = findViewById<Button>(R.id.location_bt)
        val map_bt = findViewById<Button>(R.id.map_bt)

        map_bt.setOnClickListener { v ->
            val intent = Intent(this, Maps::class.java)
            startActivity(intent)
            finish()
        }

        logout_bt.setOnClickListener { v ->
            this.onLogout(v) }

        location_bt.setOnClickListener { v ->
            val intent = Intent(v.context, LocationsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onLogout(v: View) {
        LoggedUser.logOut()
        if (!LoggedUser.testing) {
            val intent = Intent(v.context, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
