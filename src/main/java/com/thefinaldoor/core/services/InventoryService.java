package com.thefinaldoor.core.services;

import com.thefinaldoor.core.exceptions.InventoryNotFoundException;
import com.thefinaldoor.core.exceptions.PlayerNotFoundException;
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
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new InventoryNotFoundException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        InventoryResponse response = new InventoryResponse();
        response.setPlayerId(inventory.getPlayerId());
        response.setSuccess(true);
        response.setMessage("Inventory retrieved successfully");
        response.setItems(inventory.getAllItems().stream()
                .map(this::convertToGeneratedItem)
                .toList());
        return response;
    }

    public boolean addItemToInventory(String playerId, com.thefinaldoor.generated.model.Item generatedItem) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new InventoryNotFoundException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        com.thefinaldoor.asylum.model.Item asylumItem = convertToAsylumItem(generatedItem);
        inventory.addItem(asylumItem);
        inventoryRepository.save(inventory);
        return true;
    }

    public String removeItemFromInventory(String playerId, String itemId) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new InventoryNotFoundException("No inventory linked to player with ID: " + playerId);
        }

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        String itemName = inventory.removeItemAndGetName(itemId);
        if (itemName != null) {
            inventoryRepository.save(inventory);
        }
        return itemName;
    }

    private com.thefinaldoor.generated.model.Item convertToGeneratedItem(com.thefinaldoor.asylum.model.Item asylumItem) {
        com.thefinaldoor.generated.model.Item generatedItem = new com.thefinaldoor.generated.model.Item();
        generatedItem.setId(asylumItem.getId());
        generatedItem.setName(asylumItem.getName());
        generatedItem.setDescription(asylumItem.getDescription());
        generatedItem.setType(asylumItem.getType());
        generatedItem.setUse(asylumItem.getUse());
        return generatedItem;
    }

    private com.thefinaldoor.asylum.model.Item convertToAsylumItem(com.thefinaldoor.generated.model.Item generatedItem) {
        return new com.thefinaldoor.asylum.model.Item(
                generatedItem.getId(),
                generatedItem.getName(),
                generatedItem.getDescription(),
                generatedItem.getType(),
                generatedItem.getUse()
        );
    }
}

