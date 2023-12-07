package com.veero.escaperoomgame.asylum.model;

import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private String interaction;

    public Item(String id, String name, String interaction) {
        this.id = id;
        this.name = name;
        this.interaction = interaction;
    }
}
