package com.veero.escaperoomgame.core.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id; // This could be the playerId if there's a one-to-one relationship

    private List<String> itemIds; // References to Item IDs

    public void addItem(String itemId) {
        this.itemIds.add(itemId);
    }

    public boolean removeItem(String itemId) {
        return this.itemIds.remove(itemId);
    }
}
