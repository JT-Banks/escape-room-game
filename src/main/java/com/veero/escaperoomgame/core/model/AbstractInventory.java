package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractInventory {

    private final Map<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void removeItem(Item item) {
        items.remove(item.getId(), item);
    }

    public Optional<Item> getItem(String itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    public Map<String, Item> getAllItems() {
        return new HashMap<>(items);
    }

    public abstract void useItem(String itemId);

    public abstract InventoryResponse getEntireInventory(String playerId);

}
