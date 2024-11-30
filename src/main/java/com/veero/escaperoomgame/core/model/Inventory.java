package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.core.dto.InventoryResponse;

import java.io.Serializable;
import java.util.List;

public interface Inventory extends Serializable {
    void addItem(String itemId);
    boolean removeItem(String itemId);
    InventoryResponse getEntireInventory(String playerId);
    List<String> getItems();
    boolean hasItem(String itemId);
}
