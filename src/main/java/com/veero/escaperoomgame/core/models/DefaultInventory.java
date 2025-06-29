package com.veero.escaperoomgame.core.models;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;

import java.util.Optional;

public class DefaultInventory extends AbstractInventory {

    public DefaultInventory(InventoryRepository inventoryRepository) {
        super(inventoryRepository);
    }

    @Override
    public void useItem(String inventoryId, String itemId) {
        Inventory inventory = (Inventory) inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));

        Optional<Item> itemOptional = inventory.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();

        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("Item not found in inventory");
        }

        Item item = itemOptional.get();
        // Logic to "use" the item, e.g., apply its effects, remove it, etc.
        inventory.removeItem(itemId);
        inventoryRepository.save((com.veero.escaperoomgame.core.dto.Inventory) inventory);

    }

    @Override
    public InventoryResponse getEntireInventory(String inventoryId) {
        Inventory inventory = (Inventory) inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));
        return new InventoryResponse(inventory.getPlayerId(), true, "Inventory fetched successfully", inventory.getItems());
    }
}