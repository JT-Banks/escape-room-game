package com.thefinaldoor.core.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomId) {
        super(String.format("Room with ID '%s' not found", roomId));
    }
}
