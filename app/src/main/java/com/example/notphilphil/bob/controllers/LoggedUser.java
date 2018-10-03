package com.example.notphilphil.bob.controllers;

import android.util.Log;

import com.example.notphilphil.bob.models.Admin;
import com.example.notphilphil.bob.models.Employee;
import com.example.notphilphil.bob.models.Manager;
import com.example.notphilphil.bob.models.User;

import java.util.Arrays;
import java.util.List;

public class LoggedUser<T> {
    /*
    Super basic class to try and keep users with certain permissions away
    from actions that are above their permission level.
     */
    protected enum PermissionsEnum {
        NONE, USER, LOCATION_EMPLOYEE, MANAGER, ADMIN
    }
    public static List<String> legalUserTypes = Arrays.asList("User", "Admin", "Manager", "Location Employee");

    private static String name;
    private static String id;
    private static PermissionsEnum permissions;
    private static boolean loggedIn;

    private static User user;
    private static Employee employee;
    private static Manager manager;
    private static Admin admin;


    public static LoggedUser<User> newInstance(User value) {
        LoggedUser.user = value;
        return new LoggedUser<>(value);
    }

    public static LoggedUser<Employee> newInstance(Employee value) {
        LoggedUser.employee = value;
        return new LoggedUser<>(value);
    }

    private LoggedUser(T newLoggedUser) {
        LoggedUser.name = name;
        LoggedUser.id = id;
        LoggedUser.permissions = permissions;
        LoggedUser.loggedIn = true;
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

    public static boolean isLoggedIn() {
        return loggedIn;
    }

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