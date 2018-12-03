package com.example.notphilphil.bob.models

import java.io.Serializable
import java.util.Arrays

class Item @JvmOverloads constructor(var type: String? = "type", var id: String? = "id", var color: String? = "color", var price: Double = 0.0, var key: String? = "key", var category: String? = "category") : Serializable {

    /**
     * Method to add values to the object without having to know what value
     *
     * @param key the key that represents where this value should go
     * @param data the data that we are trying to put at a certain key
     */
    fun addValue(key: String, data: String) {
        when (Arrays.asList(*tokens).indexOf(key)) {
            0 -> this.type = data
            1 -> this.id = data
            2 -> this.color = data
            3 -> this.price = java.lang.Double.parseDouble(data)
            4 -> this.category = data
            else -> {
            }
        }
    }

    override fun toString(): String {
        return "" + this.color + " " + this.type + " | " + this.category + "\n" + this.price
    }

    companion object {

        internal val tokens = arrayOf("type", "id", "color", "price", "category")
    }
}
