package com.example.notphilphil.bob.models

class LocationEmployee private constructor(userID: String, name: String, private val location: Location) : User(userID, name) {

    private val employeeID: Int

    init {
        employeeID = 0
    }

    constructor(userID: String, name: String) : this(userID, name, Location()) {}

    override fun toString(): String {
        return "My employee ID is $employeeID and my location is: $location"
    }
}
