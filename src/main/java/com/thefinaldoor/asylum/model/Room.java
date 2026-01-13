package com.thefinaldoor.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;
    private String name;
    private String description;
    private boolean isLocked;
    private List<com.thefinaldoor.generated.model.Item> items;
    private List<com.thefinaldoor.generated.model.Puzzle> puzzles;
    private List<String> hints;

    public Room(String id, String name, String description, boolean isLocked,
                List<com.thefinaldoor.generated.model.Item> items,
                List<com.thefinaldoor.generated.model.Puzzle> puzzles,
                List<String> hints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.items = items;
        this.puzzles = puzzles;
        this.hints = hints;
    }

    public Room() {

    }
}
