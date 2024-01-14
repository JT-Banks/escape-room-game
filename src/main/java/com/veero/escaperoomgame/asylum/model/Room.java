package com.veero.escaperoomgame.asylum.model;

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
    private List<Item> items;
    private List<Puzzle> puzzles;
    private List<String> hints;

    public Room(String id, String name, String description, boolean isLocked,
                List<Item> items, List<Puzzle> puzzles, List<String> hints) {
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
