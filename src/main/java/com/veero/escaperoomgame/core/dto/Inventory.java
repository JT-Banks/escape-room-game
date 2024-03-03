package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Inventory {

    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }

    public boolean hasItem(String itemId) {
        return this.items.stream().anyMatch(item -> item.getId().equals(itemId));
    }

    public Item findItemById(String itemId) {
        return this.items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null); // Maybe an Optional<Item> instead
    }
}
