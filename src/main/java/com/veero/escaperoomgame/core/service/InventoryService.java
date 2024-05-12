package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.repository.ItemRepository;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.model.AbstractInventory;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService extends AbstractInventory {

    private final InventoryRepository inventoryRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
    }

    public boolean addItemToInventory(String inventoryId, String itemId) {
        Item item = itemRepository.findByItemId(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found with ID: " + itemId);
        }

        Optional<AbstractInventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        if (inventoryOptional.isPresent()) {
            AbstractInventory inventory = inventoryOptional.get();
            inventory.addItem(item);
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
        Optional<AbstractInventory> inventoryOptional = inventoryRepository.findById(playerId);
        if (inventoryOptional.isPresent()) {
            AbstractInventory inventory = inventoryOptional.get();
            InventoryResponse response = new InventoryResponse();
            response.setItems(inventory.getAllItems());
            inventoryRepository.save(inventory);
            return inventory.getEntireInventory(playerId);
        } else {
            throw new IllegalArgumentException("Inventory not found with ID: " + playerId);
        }
    }

    @Override
    public void useItem(String itemId) {

    }
}
