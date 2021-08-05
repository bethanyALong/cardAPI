package com.example.demo.services.models;

public enum ErrorCodeEnum {

    SUCCESS_CREATION("001", "User created successfully"),
    FOBIDDEN_REQUEST("004", "Forbidden request"),
    DATABASE_FAILURE("005", "Database failure due to x"),
    VALIDATION_FAILURE("006", "Validation failure due to x");


    public String errorMessage;
    public String errorCode;

    ErrorCodeEnum(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
