package com.veero.escaperoomgame.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerCreationResponse {
    private String playerId;
    private String playerName;
    private String background;
    private String difficulty;
    private String specialAbility;
    private String starterItem;
    private com.veero.escaperoomgame.core.model.Inventory inventory;
    private boolean success;
}
