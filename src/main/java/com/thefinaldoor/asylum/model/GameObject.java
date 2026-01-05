package com.thefinaldoor.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "game objects")
public class GameObject {
    @Id
    private String id;
    private String name;
    private String interactionType;
    private String roomId;
    private String interactionId;
    private String description;
    private List<String> clues;
    private List<com.thefinaldoor.generated.model.Action> actions;
    private List<String> relatedObjects;
}
