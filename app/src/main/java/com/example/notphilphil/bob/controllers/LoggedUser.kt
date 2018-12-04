package com.example.notphilphil.bob.controllers

import com.example.notphilphil.bob.models.Admin
import com.example.notphilphil.bob.models.LocationEmployee
import com.example.notphilphil.bob.models.Manager
import com.example.notphilphil.bob.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.Arrays

class LoggedUser<T> private constructor(name: String, id: String, permissions: PermissionsEnum, guest: String) {
    /*
    Super basic class to try and keep users with certain permissions away
    from actions that are above their permission level.
     */
    enum class PermissionsEnum {
        NONE,
        USER,
        LOCATION_EMPLOYEE,
        MANAGER,
        ADMIN
    }


    init {
        LoggedUser.name = name
        LoggedUser.id = id
        LoggedUser.permissions = permissions
        LoggedUser.isLoggedIn = true
        if (!testing) {
            LoggedUser.ref = FirebaseDatabase.getInstance().getReference(guest)
        }
    }

    companion object {
        val legalUserTypes = Arrays.asList("User", "Admin", "Manager", "Location Employee")

        var name: String? = null
            private set
        var id: String? = null
            private set
        var permissions: PermissionsEnum? = null
            private set
        /**
         * Method for if we are logged in.
         * @return boolean determining if we are logged in
         */
        var isLoggedIn: Boolean = false
            private set

        var ref: DatabaseReference? = null
            private set

        var testing = false

        internal fun newInstance() {
            LoggedUser<Any>("guest", "101", PermissionsEnum.NONE, "guest")
        }

        internal fun newInstance(value: User) {
            LoggedUser<Any>(value.name, value.id, PermissionsEnum.USER, "")
        }

        internal fun newInstance(value: LocationEmployee) {
            LoggedUser<Any>(value.name, value.id, PermissionsEnum.LOCATION_EMPLOYEE, "")
        }

        internal fun newInstance(value: Manager) {
            LoggedUser<Any>(value.name, value.id, PermissionsEnum.MANAGER, "")
        }

        internal fun newInstance(value: Admin) {
            LoggedUser<Any>(value.name, value.id, PermissionsEnum.ADMIN, "")
        }

        private fun setPermissions() {
            LoggedUser.permissions = PermissionsEnum.NONE
        }

        /**
         * Logs the current user out by resetting any possibly set values.
         * Note, does not change testing.
         */
        fun logOut() {
            LoggedUser.name = ""
            LoggedUser.id = ""
            LoggedUser.setPermissions()
            LoggedUser.isLoggedIn = false
            /*
        Might not need to do this if the Methods interface works well
         */
        }
    }

}
