package com.veero.escaperoomgame.asylum.model;

import lombok.Data;

@Data
public class Item {

    private String id;

    private String item;

    private String description;

    private String type;

    private String use;

    public Item(String id, String item, String description, String type, String use) {
        this.id = id;
        this.item = item;
        this.description = description;
        this.type = type;
        this.use = use;
    }

}
