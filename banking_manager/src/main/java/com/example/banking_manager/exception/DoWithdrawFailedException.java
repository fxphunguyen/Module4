package com.example.banking_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoWithdrawFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DoWithdrawFailedException(String message) {
        super(message);
    }
}
