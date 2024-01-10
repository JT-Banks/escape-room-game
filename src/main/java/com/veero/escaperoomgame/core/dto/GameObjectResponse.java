package com.veero.escaperoomgame.core.dto;

import lombok.Data;

@Data
public class GameObjectResponse {

    private String name;

    private String roomId;

    private String interactionId;

    private String description;

    public GameObjectResponse(String name, String roomId, String interactionId, String description) {
        this.name = name;
        this.description = description;
        this.roomId = roomId;
        this.interactionId = interactionId;
    }
}
