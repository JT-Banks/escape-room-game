package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "inventory")
public class Inventory {
    @Id
    private String id;
    private List<String> itemIds;
    public void addItem(String itemId) {
        this.itemIds.add(itemId);
    }
    public boolean removeItem(String itemId) {
        return this.itemIds.remove(itemId);
    }
    //TODO: Needs work, returns null
    public Map<String, Item> getAllItems() {
        return (Map<String, Item>) this.itemIds;
    }

    public InventoryResponse getEntireInventory(String playerId) {
        InventoryResponse response = new InventoryResponse();
        response.setItems(this.getAllItems());
        return response;
    }
}
