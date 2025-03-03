package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.asylum.model.Action;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

//TODO: MOVE ALL SERIALIZABLE CLASSES TO OPENAPI GENERATION!!!!!!!!!!!!
@Data
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String playerId;
    private String playerName;
    private String background;
    private String difficultyLevel;
    private String specialAbility;
    private String starterItem;
    private PlayerStatus status;
    private String currentRoomId;
    private String interactionId;
    private String inventoryId;
    private List<Action> actions;
    private double timeRemaining;
    private int score;
    public enum PlayerStatus {
        PLAYING,
        PAUSED,
        WON,
        LOST
    }

    public void linkInventory(String inventoryId) {
        this.inventoryId = inventoryId;
    }
}
