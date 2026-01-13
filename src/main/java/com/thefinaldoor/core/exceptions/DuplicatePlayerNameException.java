package com.thefinaldoor.core.exceptions;

public class DuplicatePlayerNameException extends RuntimeException {
    public DuplicatePlayerNameException(String playerName) {
        super(String.format(
                "Player name '%s' is already taken. Please choose a different name.",
                playerName)
        );
    }
}

