package com.example.sprintbootdemo.exception;

public class NoProductOnCashRegisterException extends RuntimeException {
    public NoProductOnCashRegisterException(String message) {
        super(message);
    }
}
