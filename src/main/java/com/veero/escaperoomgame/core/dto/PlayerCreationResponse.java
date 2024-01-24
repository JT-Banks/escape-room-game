package com.veero.escaperoomgame.core.dto;

import lombok.Data;

@Data
public class PlayerCreationResponse {

    private String playerId;

    private String playerName;

    private String background;

    private String difficulty;

    private String specialAbility;

    private String starterItem;

    private boolean success;

    public PlayerCreationResponse(String playerId, String playerName, String background, String difficulty, String specialAbility, String starterItem) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.background = background;
        this.difficulty = difficulty;
        this.specialAbility = specialAbility;
        this.starterItem = starterItem;
        this.success = true;

    }
}
