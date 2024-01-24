package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.core.dto.InventoryItem;
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

    public enum PlayerStatus {
        PLAYING,
        PAUSED,
        WON,
        LOST
    }

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

    private List<InventoryItem> inventory;

    private List<Action> actions;

    private double timeRemaining;

    private int score;

    public void applyTimePenalty(double penalty) {
        this.timeRemaining -= penalty;

        if (this.timeRemaining < 0) {
            this.timeRemaining = 0;
            this.status = PlayerStatus.LOST;
        }
    }

}
