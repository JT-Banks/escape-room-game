package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class InventoryItem {

    private List<Item> items;
    private String itemId;
    private String itemName;

    public InventoryItem() {
        this.items = new ArrayList<>();
    }

    public void addItemToInventory(Item item) {
        this.items.add(item);
    }

    public boolean removeItemFromInventory(Item item) {
        return this.items.remove(item);
    }

    public boolean hasItem(Item item) {
        return this.items.contains(item);
    }

    public String findItemById(String itemId) {
        for (Item item : this.items) {
            if (item.getId().equals(itemId)) {
                return item.getName();
            }
        }
        return "Item " + itemId + " not found";
    }
}
