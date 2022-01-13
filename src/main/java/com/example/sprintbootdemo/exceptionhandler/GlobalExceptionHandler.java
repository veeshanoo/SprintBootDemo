package com.example.sprintbootdemo.exceptionhandler;

import com.example.sprintbootdemo.exception.MissingFieldsException;
import com.example.sprintbootdemo.exception.NoProductOnCashRegisterException;
import com.example.sprintbootdemo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException exception) {
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Reason: ", exception.getMessage());
        responseParameters.put("Time: ", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseParameters);
    }

    @ExceptionHandler(MissingFieldsException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(RuntimeException exception) {
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Reason: ", exception.getMessage());
        responseParameters.put("Time: ", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseParameters);
    }

    @ExceptionHandler(NoProductOnCashRegisterException.class)
    public ResponseEntity<Map<String, String>> handleNoProductOnCashRegister(RuntimeException exception) {
        Map<String, String> responseParameters = new HashMap<>();
        responseParameters.put("Reason: ", exception.getMessage());
        responseParameters.put("Time: ", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseParameters);
    }
}
