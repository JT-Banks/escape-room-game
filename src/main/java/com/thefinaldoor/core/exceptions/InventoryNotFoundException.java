package com.thefinaldoor.core.exceptions;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String inventoryId) {
        super(String.format("Inventory with ID '%s' not found", inventoryId));
    }
}
