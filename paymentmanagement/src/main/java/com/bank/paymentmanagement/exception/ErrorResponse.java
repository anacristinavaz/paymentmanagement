package com.bank.paymentmanagement.exception;

public class ErrorResponse {
    private String error_code;
    private String error_description;

    public ErrorResponse(String error_code, String error_description) {
        this.error_code = error_code;
        this.error_description = error_description;
    }

    public String getError_code() {
        return error_code;
    }

    public String getError_description() {
        return error_description;
    }
}
