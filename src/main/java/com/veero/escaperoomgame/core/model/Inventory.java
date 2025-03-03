package com.veero.escaperoomgame.core.model;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;

import java.io.Serializable;
import java.util.List;

public interface Inventory extends Serializable {
    String getPlayerId();
    void setPlayerId(String playerId);
    void addItem(Item itemId);
    boolean removeItem(String itemId);
    InventoryResponse getEntireInventory(String playerId);
    List<Item> getItems();
    boolean hasItem(String itemId);
}
