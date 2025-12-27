package com.thefinaldoor.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

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

    private Map<Integer, String> notes;

    public Item(String id, String itemId, String name, String description, String type, String use) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.use = use;
    }

    public void use(String input) {
        switch (name) {
            case "notepad & pen":
                int nextKey = notes.size() + 1;
                notes.put(nextKey, input);
                break;
            case "key":
                // unlock door
                break;
            default:
                throw new IllegalArgumentException("Unsupported Item: " + name);
        }
    }
}
