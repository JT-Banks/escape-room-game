package com.thefinaldoor.asylum.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "items")
public class Item {

    @Id
    private String id;

    private String name;

    private String description;

    private String type;

    private String use;

    private Map<Integer, String> notes;

    public Item(String id, String name, String description, String type, String use) {
        this.id = id;
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
