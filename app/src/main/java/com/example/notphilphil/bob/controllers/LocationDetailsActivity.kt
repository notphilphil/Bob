package com.example.notphilphil.bob.controllers

import android.content.Context
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Item
import com.example.notphilphil.bob.models.Location
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList
import java.util.LinkedHashSet
import java.util.Objects

class LocationDetailsActivity : AppCompatActivity() {
    private var key: String? = null
    private var items: ArrayList<Item>? = null
    private var item_search: SearchView? = null
    private var category_spinner: Spinner? = null
    private val categories = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)
        key = this.intent.getStringExtra("key")
        items = ArrayList()

        val item_list = findViewById<ListView>(R.id.item_list)
        populateList(item_list, this)
        handleInfo(findViewById(R.id.info_btn), this)

        item_search = findViewById<View>(R.id.item_search) as SearchView
        category_spinner = findViewById(R.id.category_spinner)

        val add_btn = findViewById<FloatingActionButton>(R.id.add_btn)
        add_btn.setOnClickListener { v ->
            val intent = Intent(this, ModifyItemActivity::class.java)
            intent.putExtra("inventoryKey", key)
            intent.putExtra("edit", false)
            startActivityForResult(intent, 0)
        }
    }

    private fun populateList(item_list: ListView, context: Context) {
        val ref = LoggedUser.ref!!.child("inventories").child(key!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val names = ArrayList<String>()
                for (item in dataSnapshot.children) {
                    val newItem = Item()
                    items!!.clear()
                    for (`val` in item.children) {
                        newItem.addValue(`val`.key!!, `val`.value.toString())
                    }
                    newItem.key = item.key
                    items!!.add(newItem)
                    names.add(newItem.toString())
                    categories.add(newItem.category!!.toLowerCase())
                }
                val categoriesWithoutDuplicates = LinkedHashSet(categories)
                categories.clear()
                categories.addAll(categoriesWithoutDuplicates)
                val itemsAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, names)
                val categoryAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,
                        categories)
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                category_spinner!!.adapter = categoryAdapter
                item_list.adapter = itemsAdapter
                item_list.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(context, ItemDetailsActivity::class.java)
                    intent.putExtra("itemKey", items!![position].key)
                    intent.putExtra("inventoryKey", key)
                    startActivity(intent)
                }



                item_search!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        //itemsAdapter.getFilter().filter(query);
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        itemsAdapter.filter.filter(newText)

                        if (itemsAdapter.isEmpty) {
                            Toast.makeText(this@LocationDetailsActivity, "No Match found", Toast.LENGTH_LONG).show()
                        }
                        return false
                    }
                })

                category_spinner!!.onItemSelectedListener = object : OnItemSelectedListener {
                    override fun onItemSelected(spinner: AdapterView<*>, container: View,
                                                position: Int, id: Long) {
                        itemsAdapter.filter.filter(category_spinner!!.selectedItem.toString())
                    }

                    override fun onNothingSelected(arg0: AdapterView<*>) {

                    }
                }
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Probably do nothing.
                Log.w("LocationDetailsLogs", databaseError.details)
            }
        })
    }

    private fun handleInfo(info_btn: FloatingActionButton, context: Context) {
        val ref = LoggedUser.ref!!.child("locations").child(key!!)
        Log.d("LocationDetailsLogs", "Looking at " + key!!)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var body = ""
                val title = dataSnapshot.child("Name").value.toString()
                for (token in Location.tokens) {
                    body += token + ": " + dataSnapshot.child(token).value.toString() + "\n"
                }
                val finalBody = body
                info_btn.setOnClickListener { v -> AlertDialog.Builder(context).setMessage(finalBody).setTitle(title).setCancelable(true).show() }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
