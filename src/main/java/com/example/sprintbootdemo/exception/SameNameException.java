package com.example.sprintbootdemo.exception;

public class SameNameException extends RuntimeException {
    public SameNameException(String message) {
        super(message);
    }
}
