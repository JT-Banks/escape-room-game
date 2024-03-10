package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;

public interface Inventory {
    void addItem(String itemId);

    boolean removeItem(String itemId);

    InventoryResponse getEntireInventory(String playerId);
}
