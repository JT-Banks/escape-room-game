package com.veero.escaperoomgame.core.dto;

public class InventoryResponse {

    private boolean success;

    private String itemDescription;

    public InventoryResponse(boolean success) {
        this.success = success;
    }
}
