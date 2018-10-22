package com.example.notphilphil.bob.errors;

public class InvalidLocationException extends Exception {
    InvalidLocationException() {}

    public InvalidLocationException(String message) {
        super(message);
    }
}
