package com.veero.escaperoomgame.asylum.model;

import lombok.Data;

@Data
public class Item {

    private String id;

    private String name;

    private String description;

    private String type;

    private String use;

    public Item(String id, String name, String description, String type, String use) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.use = use;
    }

}
