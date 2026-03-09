package com.example.demo.exception;

public class ApiException extends RuntimeException {

    private int httpCode;
    public ApiException(int httpCode, String mesage) {
        super(mesage);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
