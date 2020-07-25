package com.pomelo.ddd.core.exception;

public class CommandNotFoundException extends RuntimeException {


    public CommandNotFoundException(String message) {
        super(message);
    }
}
