package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.core.model.AbstractInventory;
import lombok.Data;

import java.util.List;

@Data
public class PlayerCreationResponse {

    private String playerId;

    private String playerName;

    private String background;

    private String difficulty;

    private String specialAbility;

    private String starterItem;

    private AbstractInventory inventory;

    private List<Action> actions;

    private boolean success;

    public PlayerCreationResponse(
            String playerId, String playerName, String background, String difficulty,
            String specialAbility, String starterItem, AbstractInventory inventory, List<Action> actions
    ) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.background = background;
        this.difficulty = difficulty;
        this.specialAbility = specialAbility;
        this.starterItem = String.valueOf(starterItem);
        this.inventory = inventory;
        this.actions = actions;
        this.success = true;

    }
}
