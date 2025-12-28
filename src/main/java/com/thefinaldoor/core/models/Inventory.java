package com.thefinaldoor.core.models;

import com.thefinaldoor.asylum.model.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "inventory")
public class Inventory implements Serializable {
    @Id
    private String playerId;
    private List<Item> items = new ArrayList<>();

    public void addItem(String id, String itemId, String name, String description, String type, String use) {
        Item item = new Item(id, itemId, name, description, type, use);
        this.items.add(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean removeItem(String itemId) {
        return this.items.removeIf(item -> item.getItemId().equals(itemId));
    }

    public List<Item> getAllItems() {
        return this.items;
    }
}

