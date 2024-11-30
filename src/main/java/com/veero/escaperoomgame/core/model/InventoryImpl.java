package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.core.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.veero.escaperoomgame.core.Constants.INVENTORY_CANNOT_BE_EMPTY;
import static com.veero.escaperoomgame.core.Constants.PLAYER_CREATED;

public class InventoryImpl implements Inventory, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<String> items;

    @Autowired
    public InventoryImpl() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(String item) {
        this.items.add(item);
    }

    @Override
    public boolean removeItem(String item) {
        return this.items.remove(item);
    }

    @Override
    public InventoryResponse getEntireInventory(String playerId) {
        if (this.items.isEmpty()) {
            return new InventoryResponse(playerId, false, INVENTORY_CANNOT_BE_EMPTY);
        }
        return new InventoryResponse(playerId, true, PLAYER_CREATED);
    }

    @Override
    public List<String> getItems() {
        return this.items;
    }

    @Override
    public boolean hasItem(String itemId) {
        return this.items.contains(itemId);
    }
}