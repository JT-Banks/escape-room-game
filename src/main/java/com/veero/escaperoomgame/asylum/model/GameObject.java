package com.veero.escaperoomgame.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "game_objects")
public class GameObject {
    @Id
    private String id;
    private String interactionId;
    private String roomId;
    private String name;
    private InteractionType interactionType;
}
