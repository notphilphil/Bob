package com.example.notphilphil.bob.models

import java.util.Arrays

class Location {
    var zip: String? = null
        private set
    var type: String? = null
        private set
    var phone: String? = null
        private set
    var state: String? = null
        private set
    var streetAddress: String? = null
        private set
    var website: String? = null
        private set
    var latitude: Double = 0.toDouble()
        private set
    var longitude: Double = 0.toDouble()
        private set
    var city: String? = null
        private set
    var name: String? = null
        private set
    var key: String? = null

    private constructor(zip: String, type: String, phone: String, state: String, streetAddress: String, website: String, city: String, name: String, key: String, lat: String, lon: String) {
        this.zip = zip
        this.type = type
        this.phone = phone
        this.state = state
        this.streetAddress = streetAddress
        this.website = website
        latitude = java.lang.Double.parseDouble(lat)
        longitude = java.lang.Double.parseDouble(lon)
        this.city = city
        this.name = name
        this.key = key
    }

    constructor() {

    }

    fun addValue(key: String, data: String) {
        when (Arrays.asList(*tokens).indexOf(key)) {
            0 -> this.latitude = java.lang.Double.parseDouble(data)
            1 -> this.longitude = java.lang.Double.parseDouble(data)
            2 -> this.streetAddress = data
            3 -> this.city = data
            4 -> this.state = data
            5 -> this.zip = data
            6 -> this.type = data
            7 -> this.phone = data
            8 -> this.website = data
            9 -> this.name = data
        }
    }

    override fun toString(): String {
        return "Latitude: " + this.latitude +
                "\nLongitude: " + this.longitude +
                "\nStreet Address: " + this.streetAddress +
                "\nCity: " + this.city +
                "\nState: " + this.state +
                "\nZip: " + this.zip +
                "\nType: " + this.type +
                "\nPhone: " + this.phone +
                "\nWebsite: " + this.website
    }

    companion object {

        val tokens = arrayOf("Latitude", "Longitude", "Street Address", "City", "State", "Zip", "Type", "Phone", "Website", "Name")
    }
}
