package com.smarthub.baseapplication.model;

public class APIError {
    private int error_code;
    private String message;
    private String status;

    public APIError() {
    }

    public int getError_code() {
        return error_code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
