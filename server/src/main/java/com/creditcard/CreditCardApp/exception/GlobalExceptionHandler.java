package com.creditcard.CreditCardApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Validation errors (@Valid)
    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field, message);
                });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // 🔹 Illegal arguments (business rules)
    @SuppressWarnings("unused")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex) {

        return errorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    // 🔹 Fallback (safety net)
    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {

        return errorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error occurred"
        );
    }

    private ResponseEntity<Map<String, Object>> errorResponse(
            HttpStatus status,
            String message) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("message", message);

        return ResponseEntity.status(status).body(response);
    }
}
