package com.example.notphilphil.bob.controllers

import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

import com.example.notphilphil.bob.R
import com.example.notphilphil.bob.models.Item

import java.util.Objects

class ModifyItemActivity : AppCompatActivity() {
    internal lateinit var type_et: EditText
    internal lateinit var color_et: EditText
    internal lateinit var id_et: EditText
    internal lateinit var price_et: EditText
    internal lateinit var category_et: EditText
    internal var edit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_item)

        type_et = findViewById(R.id.type_et)
        color_et = findViewById(R.id.color_et)
        id_et = findViewById(R.id.id_et)
        price_et = findViewById(R.id.price_et)
        category_et = findViewById(R.id.category_et)
        edit = this.intent.getBooleanExtra("edit", false)

        if (edit) populatePage(this.intent.getSerializableExtra("item") as Item)

        val save_btn = findViewById<FloatingActionButton>(R.id.save_btn)
        val trash_btn = findViewById<FloatingActionButton>(R.id.trash_btn)

        trash_btn.setOnClickListener { v -> this.onBackPressed() }
        save_btn.setOnClickListener { v -> this.onSave(intent) }
    }

    internal fun onSave(intent: Intent): Item? {
        val key: String
        val invKey: String
        val item = Item()
        item.type = type_et.text.toString()
        item.id = id_et.text.toString()
        item.color = color_et.text.toString()
        item.price = java.lang.Double.parseDouble(price_et.text.toString())
        item.category = category_et.text.toString()
        //if (!LoggedUser.getTesting()) {
            invKey = intent.getStringExtra("inventoryKey")
            if (edit)
                key = intent.getStringExtra("itemKey")
            else
                key = LoggedUser.ref!!.child(intent.getStringExtra("inventoryKey")).push().getKey()!!
            item.key = key
            LoggedUser.ref!!.child("inventories").child(invKey).child((key)).setValue(item)
            finish()
            return null
        //} else
            return item
    }

    internal fun populatePage(item: Item) {
        type_et.setText(item.type)
        color_et.setText(item.color)
        id_et.setText(item.id)
        price_et.setText(item.price.toString())
        category_et.setText(item.category)
    }
}
