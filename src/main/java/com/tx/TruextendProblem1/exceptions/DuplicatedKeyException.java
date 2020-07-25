package com.tx.TruextendProblem1.exceptions;

public class DuplicatedKeyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicatedKeyException(String message) {
        super(message+" already exists");
    }
}
