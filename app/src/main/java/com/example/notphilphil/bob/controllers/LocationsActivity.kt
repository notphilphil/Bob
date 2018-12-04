package com.example.notphilphil.bob.controllers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Location
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList
import java.util.Objects

class LocationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        val home_bt = findViewById<Button>(R.id.home_bt)
        val location_list = findViewById<ListView>(R.id.location_list)

        handleAlerts(location_list, this)    // Handle alerts to initialize locations

        home_bt.setOnClickListener { v ->
            val intent = Intent(v.context, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleAlerts(location_list: ListView, context: Context) {
        val ref = LoggedUser.ref!!.child("locations")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val locations = ArrayList<Location>()
                val names = ArrayList<String>()
                val locs = dataSnapshot.children
                for (loc in locs) {
                    val newLoc = Location()
                    names.add(loc.child("Name").value.toString())
                    for (`val` in loc.children) newLoc.addValue(`val`.key!!, `val`.value.toString())
                    newLoc.key = loc.key
                    locations.add(newLoc)
                }
                val locationsAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, names)
                location_list.adapter = locationsAdapter as ListAdapter?
                location_list.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(parent.context, LocationDetailsActivity::class.java)
                    intent.putExtra("key", locations[position].key)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Probably do nothing.
                Log.w("LocationsActivity", databaseError.details)
            }
        })
    }
}