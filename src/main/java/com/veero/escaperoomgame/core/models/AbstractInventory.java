package com.veero.escaperoomgame.core.models;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInventory {

    protected InventoryRepository inventoryRepository;

    public AbstractInventory(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addItem(String inventoryId, Item item) {
        Inventory inventory = (Inventory) inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));
        inventory.addItem(item);
        inventoryRepository.save((com.veero.escaperoomgame.core.dto.Inventory) inventory);
    }

    public void removeItem(String inventoryId, String itemId) {
        Inventory inventory = (Inventory) inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));
        inventory.removeItem(itemId);
        inventoryRepository.save((com.veero.escaperoomgame.core.dto.Inventory) inventory);
    }

    public Map<String, Item> getAllItems(String inventoryId) {
        Inventory inventory = (Inventory) inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));
        Map<String, Item> itemsMap = new HashMap<>();
        for (Item item : inventory.getItems()) {
            itemsMap.put(item.getId(), item);
        }
        return itemsMap;
    }
  
    public abstract void useItem(String inventoryId, String itemId);

    public abstract InventoryResponse getEntireInventory(String inventoryId);
}

