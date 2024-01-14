package com.veero.escaperoomgame.asylum.model;

import com.veero.escaperoomgame.asylum.dto.Inventory;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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

    private PlayerStatus status;

    private String playerId;

    private String name;

    private String currentRoomId;

    private String interactionId;

    private Inventory inventory;

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
