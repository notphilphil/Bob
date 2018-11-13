package com.example.notphilphil.bob.controllers;

import android.content.Context;

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
    public static List<String> legalUserTypes = Arrays.asList("User", "Admin", "Manager", "Location Employee");

    private static String name;
    private static String id;
    private static PermissionsEnum permissions;
    private static boolean loggedIn;

    private static User user;
    private static LocationEmployee employee;
    private static Manager manager;
    private static Admin admin;

    private static DatabaseReference ref;

    private static boolean testing = false;


    static void newInstance(User value, Context cont) {
        LoggedUser.user = value;
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.USER, cont);
    }

    static void newInstance(LocationEmployee value, Context cont) {
        LoggedUser.employee = value;
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.LOCATION_EMPLOYEE, cont);
    }
    
    static void newInstance(Manager value, Context cont) {
        LoggedUser.manager = value;
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.MANAGER, cont);
    }

    static void newInstance(Admin value, Context cont) {
        LoggedUser.admin = value;
        new LoggedUser<>(value.getName(), value.getID(), PermissionsEnum.ADMIN, cont);
    }

    private LoggedUser(String name, String id, PermissionsEnum permissions, Context cont) {
        LoggedUser.name = name;
        LoggedUser.id = id;
        LoggedUser.permissions = permissions;
        LoggedUser.loggedIn = true;
        if (!testing) LoggedUser.ref = FirebaseDatabase.getInstance().getReference();
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoggedUser.name = name;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        LoggedUser.id = id;
    }

    public static PermissionsEnum getPermissions() {
        return permissions;
    }

    public static void setPermissions(PermissionsEnum permissions) {
        LoggedUser.permissions = permissions;
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
        LoggedUser.setPermissions(PermissionsEnum.NONE);
        LoggedUser.loggedIn = false;
        LoggedUser.user = null;
        LoggedUser.employee = null;
        LoggedUser.manager = null;
        LoggedUser.admin = null;
        /*
        Might not need to do this if the Methods interface works well
         */
    }

    /**
     * Philip Glover 10/3/2018
     * IMPORTANT! This is where all methods used by primary actors in our
     * application will be "chained" into their relevant classes.
     *
     * Because we have multiple classes that implement the same method, I ran
     * into a problem of not wanting to pass around a user object to every
     * activity that used it, so instead of renaming methods and trusting the
     * programmer to know which one to call based on the situation, all you have
     * to do is call the method in that activity and add the correct code in this
     * switch statement to handle which method to call based on their permissions
     * or their logged in status.
     *
     * Example: say you are on the home page and would like to include the toString()
     * of the current logged in user in some TextField. Instead of having to check what
     * type of user is logged in, just call toString() and in this interface you would
     * write something like
     * ...
     * static void toString() {
     *     switch (permissions) {
     *         case NONE: // handle no permissions then break
     *         case USER: // handle user permissions
     *              user.toString();
     *         ...
     *         default: // handle no set permissions (throw error most likely)
     *     }
     * }
     * ...
     */
    public interface Methods {

    }
}
