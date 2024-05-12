package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class InventoryResponse {

    private boolean success;

    @NonNull
    private String playerId;

    private String message;

    @Setter
    private Map<String, Item> items;

    public InventoryResponse(@NonNull String playerId, boolean success, String message) {
        this.playerId = playerId;
        this.success = success;
        this.message = message;
    }

}