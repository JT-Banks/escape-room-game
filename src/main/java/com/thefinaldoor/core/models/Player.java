package com.thefinaldoor.core.models;

import com.thefinaldoor.generated.model.Action;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "player")
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String playerId;

    @Indexed(unique = true)
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
    @CreatedDate
    private LocalDateTime createdAt;

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
