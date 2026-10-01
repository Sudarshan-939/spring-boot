package com.suda.studentapi.exception;

public class DuplicateRegisterNumberException
        extends RuntimeException {

    public DuplicateRegisterNumberException(String message) {
        super(message);
    }
}