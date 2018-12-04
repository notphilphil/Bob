package com.example.notphilphil.bob.models

open class User(val id: String, val name: String) {

    internal constructor() : this("jDoe3", "John Doe") {}

    override fun toString(): String {
        return "My name is $name and my user ID is: $id"
    }

}
