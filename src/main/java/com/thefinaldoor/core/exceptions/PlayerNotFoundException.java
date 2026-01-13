package com.thefinaldoor.core.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String playerId) {
        super(String.format("Player with ID '%s' not found", playerId));
    }
}
