package com.veero.escaperoomgame.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "items")
public class Item {

    @Id
    private String id;

    @Field("itemId")
    private String itemId;

    private String name;

    private String description;

    private String type;

    private String use;

    public Item(String id, String itemId, String name, String description, String type, String use) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.use = use;
    }

}
