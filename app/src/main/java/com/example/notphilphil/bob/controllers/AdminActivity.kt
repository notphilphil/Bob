package com.example.notphilphil.bob.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Location
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList
import java.util.Objects

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val home_bt = findViewById<Button>(R.id.home_bt)
        val user_list = findViewById<ListView>(R.id.user_list)

        handleAlerts(user_list, this)    // Handle alerts to initialize locations

        home_bt.setOnClickListener { v ->
            val intent = Intent(v.context, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleAlerts(user_list: ListView, context: Context) {
        val ref = LoggedUser.ref!!.child("locations")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val allUser = ArrayList<String>()
                val names = dataSnapshot.children
                for (name in names) {
                    allUser.add(name.key + " - " + name.getValue(String::class.java))
                }

                val locationsAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, allUser)
                user_list.adapter = locationsAdapter
                user_list.setOnItemClickListener { parent, view, position, id ->
                    val name: String
                    val status: String
                    name = allUser[position].split(" - ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    status = allUser[position].split(" - ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                    if (status == "lock")
                        LoggedUser.ref!!.child("user").child(name).setValue("unlock")
                    else
                        LoggedUser.ref!!.child("user").child(name).setValue("lock")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Probably do nothing.
                Log.w("LocationsActivity", databaseError.details)
            }
        })
    }
}
