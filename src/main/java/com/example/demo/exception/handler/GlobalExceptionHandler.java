package com.example.demo.exception.handler;

import com.example.demo.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getHttpCode()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(exception = {BindException.class})
    public ResponseEntity<Object> handleBindException(BindException b) {
        return ResponseEntity.status(400).body(b.getAllErrors().get(0).getDefaultMessage());
    }

}
