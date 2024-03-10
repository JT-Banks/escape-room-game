package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.repository.ItemRepository;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.model.Inventory;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
    }

    public boolean addItemToInventory(String inventoryId, String itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Item not found with ID: " + itemId);
        }

        // Fetch the inventory and add the item
        Optional<com.veero.escaperoomgame.core.dto.Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = (Inventory) inventoryOptional.get();
            inventory.addItem(itemId);
            inventoryRepository.save(inventory);
            return true;
        } else {
            throw new IllegalArgumentException("Inventory not found with ID: " + inventoryId);
        }
    }

    public InventoryResponse getEntireInventory(String playerId) {
        if (!inventoryRepository.existsById(playerId)) {
            throw new IllegalArgumentException("Inventory not found with ID: " + playerId);
        }

        // Fetch the inventory and add the item
        Optional<com.veero.escaperoomgame.core.dto.Inventory> inventoryOptional = inventoryRepository.findById(playerId);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = (Inventory) inventoryOptional.get();
            inventory.getEntireInventory(playerId);
            inventoryRepository.save(inventory);
            return inventory.getEntireInventory(playerId);
        } else {
            throw new IllegalArgumentException("Inventory not found with ID: " + playerId);
        }
    }

}
