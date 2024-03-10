package com.veero.escaperoomgame.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class InventoryResponse {

    private boolean success;

    @NonNull
    private String playerId;

    private String message;

    public InventoryResponse(@NonNull String playerId, boolean success, String message) {
        this.playerId = playerId;
        this.success = success;
        this.message = message;
    }
}