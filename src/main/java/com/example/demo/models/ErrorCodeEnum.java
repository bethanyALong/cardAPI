package com.example.demo.models;

public enum ErrorCodeEnum {

    SUCCESS_CREATION("001", "User created successfully"),
    FOBIDDEN_REQUEST("004", "Forbidden request"),
    DATABASE_FAILURE("005", "Database failure due to internal error"),
    VALIDATION_FAILURE("006", "Validation failure due to %x"),
    SUCCESS_GET("007", "User successfully retrieved"),
    USER_NOT_FOUND("008", "User not found"),
    SUCCESS_UPDATE("009", "User stores successfully updated");



    public String responseMessage;
    public String responseCode;

    ErrorCodeEnum(String responseCode, String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
