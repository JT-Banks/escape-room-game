package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultInventory extends AbstractInventory {

    private List<Item> items = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        super.addItem(item);
    }

    @Override
    public Optional<Item> getItem(String itemId) {
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst();
    }

    public void useItem(String itemId) {
        // Implement the useItem method
    }

    @Override
    public InventoryResponse getEntireInventory(String playerId) {
        // Implement the getEntireInventory method
        return null;
    }
}