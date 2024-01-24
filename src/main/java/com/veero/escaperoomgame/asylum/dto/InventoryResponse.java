package com.veero.escaperoomgame.asylum.dto;

import org.springframework.stereotype.Component;

@Component
public class InventoryResponse {

    private boolean success;

    private String itemDescription;

    public InventoryResponse(boolean success) {
        this.success = success;
    }
}
