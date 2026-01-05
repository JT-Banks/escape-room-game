package com.thefinaldoor.core.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemId) {
        super(String.format("Item with ID '%s' not found", itemId));
    }
}
