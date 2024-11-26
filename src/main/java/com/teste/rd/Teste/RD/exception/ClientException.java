package com.teste.rd.Teste.RD.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {
    private final ErrorCode errorCode;

    public ClientException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}