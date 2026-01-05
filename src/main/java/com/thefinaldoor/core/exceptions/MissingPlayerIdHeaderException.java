package com.thefinaldoor.core.exceptions;

public class MissingPlayerIdHeaderException extends RuntimeException {
    public MissingPlayerIdHeaderException() {
        super("X-Player-Id header is required for this operation");
    }
}

