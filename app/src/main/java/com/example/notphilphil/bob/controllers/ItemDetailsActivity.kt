package com.example.notphilphil.bob.controllers

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Item
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

import java.util.Objects

class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        populatePage(this)

        findViewById<Button>(R.id.return_btn).setOnClickListener { v -> this.onBackPressed() }
    }

    private fun populatePage(context: Context) {
        val itemKey = intent.getStringExtra("itemKey")
        val inventoryKey = intent.getStringExtra("inventoryKey")
        val ref = LoggedUser.ref!!.child("inventories").child(inventoryKey).child(itemKey)


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val edit_btn = findViewById<Button>(R.id.edit_btn)
                val delete_btn = findViewById<Button>(R.id.delete_btn)

                if (dataSnapshot.exists()) {
                    val type_tv = findViewById<TextView>(R.id.type_tv)
                    val color_tv = findViewById<TextView>(R.id.color_tv)
                    val id_tv = findViewById<TextView>(R.id.id_tv)
                    val price_tv = findViewById<TextView>(R.id.price_tv)
                    val category_tv = findViewById<TextView>(R.id.category_tv)
                    val item = Item()
                    for (`val` in dataSnapshot.children) {
                        item.addValue(`val`.key!!, `val`.value.toString())
                    }
                    type_tv.text = dataSnapshot.child("type").value.toString()
                    color_tv.text = dataSnapshot.child("color").value.toString()
                    id_tv.text = dataSnapshot.child("id").value.toString()
                    price_tv.text = dataSnapshot.child("price").value.toString()
                    category_tv.text = dataSnapshot.child("category").value.toString()

                    edit_btn.setOnClickListener { v ->
                        val intent = Intent(context, ModifyItemActivity::class.java)
                        intent.putExtra("item", item)
                        intent.putExtra("itemKey", itemKey)
                        intent.putExtra("inventoryKey", inventoryKey)
                        intent.putExtra("edit", true)
                        startActivity(intent)
                    }
                }

                delete_btn.setOnClickListener { v ->
                    onBackPressed()
                    ref.removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
