package com.avaliacao.rd.client.RD.exception;

public class ErrorResponse {

    private ErrorCode errorCode;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    // Getters e setters
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
