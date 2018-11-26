package com.example.notphilphil.bob.controllers;

import com.example.notphilphil.bob.models.Admin;
import com.example.notphilphil.bob.models.LocationEmployee;
import com.example.notphilphil.bob.models.Manager;
import com.example.notphilphil.bob.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class LoggedUser<T> {
    /*
    Super basic class to try and keep users with certain permissions away
    from actions that are above their permission level.
     */
    protected enum PermissionsEnum {
        NONE,
        USER,
        LOCATION_EMPLOYEE,
        MANAGER,
        ADMIN
    }
    public static final List<String> legalUserTypes = Arrays.asList("User", "Admin", "Manager", "Location Employee");

    private static String name;
    private static String id;
    private static PermissionsEnum permissions;
    private static boolean loggedIn;

    private static DatabaseReference ref;

    private static boolean testing = false;

    static void newInstance() {
        new LoggedUser<>("guest", "101", PermissionsEnum.NONE, "guest");
    }

    static void newInstance(User value) {
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.USER, "");
    }

    static void newInstance(LocationEmployee value) {
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.LOCATION_EMPLOYEE, "");
    }
    
    static void newInstance(Manager value) {
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.MANAGER, "");
    }

    static void newInstance(Admin value) {
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.ADMIN, "");
    }


    private LoggedUser(String name, String id, PermissionsEnum permissions, String guest) {
        LoggedUser.name = name;
        LoggedUser.id = id;
        LoggedUser.permissions = permissions;
        LoggedUser.loggedIn = true;
        if (!testing) {
            LoggedUser.ref = FirebaseDatabase.getInstance().getReference(guest);
        }
    }

    public static String getName() {
        return name;
    }

    private static void setName(String name) {
        LoggedUser.name = name;
    }

    public static String getId() {
        return id;
    }

    private static void setId(String id) {
        LoggedUser.id = id;
    }

    public static PermissionsEnum getPermissions() {
        return permissions;
    }

    private static void setPermissions() {
        LoggedUser.permissions = PermissionsEnum.NONE;
    }

    public static DatabaseReference getRef() {
        return ref;
    }

    /**
     * Method for if we are logged in.
     * @return boolean determining if we are logged in
     */
    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setTesting(boolean newTesting) {
        testing = newTesting;
    }

    public static boolean getTesting() { return testing; }

    /**
     * Logs the current user out by resetting any possibly set values.
     * Note, does not change testing.
     */
    public static void logOut() {
        LoggedUser.setName("");
        LoggedUser.setId("");
        LoggedUser.setPermissions();
        LoggedUser.loggedIn = false;
        /*
        Might not need to do this if the Methods interface works well
         */
    }

}
