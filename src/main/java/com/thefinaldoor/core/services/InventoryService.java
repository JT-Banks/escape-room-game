package com.thefinaldoor.core.services;

import com.thefinaldoor.asylum.model.Item;
import com.thefinaldoor.core.repositories.PlayerRepository;
import com.thefinaldoor.generated.model.InventoryResponse;
import com.thefinaldoor.core.models.Inventory;
import com.thefinaldoor.core.models.Player;
import com.thefinaldoor.core.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final PlayerRepository playerRepository;

    public InventoryService(InventoryRepository inventoryRepository, PlayerRepository playerRepository) {
        this.inventoryRepository = inventoryRepository;
        this.playerRepository = playerRepository;
    }

    public InventoryResponse getEntireInventory(String playerId) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new IllegalArgumentException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));

        InventoryResponse response = new InventoryResponse();
        response.setPlayerId(inventory.getPlayerId());
        response.setSuccess(true);
        response.setMessage("Inventory retrieved successfully");
        return response;
    }

    public boolean addItemToInventory(String playerId, Item item) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new IllegalArgumentException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));

        inventory.addItem(item);
        inventoryRepository.save(inventory);
        return true;
    }

    public boolean removeItemFromInventory(String playerId, String itemId) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new IllegalArgumentException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with ID: " + inventoryId));

        boolean removed = inventory.removeItem(itemId);
        if (removed) {
            inventoryRepository.save(inventory);
        }
        return removed;
    }
}

