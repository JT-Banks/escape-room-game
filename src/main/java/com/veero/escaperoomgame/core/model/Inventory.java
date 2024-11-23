package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.core.dto.InventoryResponse;

import java.util.List;

public interface Inventory {
    void addItem(String itemId);
    boolean removeItem(String itemId);
    InventoryResponse getEntireInventory(String playerId);
    List<String> getItems();
    boolean hasItem(String itemId);
}
