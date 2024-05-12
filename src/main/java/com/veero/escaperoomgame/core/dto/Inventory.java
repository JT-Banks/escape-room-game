package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "inventory")
public class Inventory {

    @Id
    private String id;

    private List<String> itemIds;

    public void addItems(List<Item> items) {
        for (Item item : items) {
            this.itemIds.add(item.getId());
        }
    }

    public boolean removeItem(String itemId) {
        return this.itemIds.remove(itemId);
    }
}
