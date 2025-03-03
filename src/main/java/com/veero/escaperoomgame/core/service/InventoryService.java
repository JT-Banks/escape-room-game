package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.repository.ItemRepository;
import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.model.AbstractInventory;
import com.veero.escaperoomgame.core.model.DefaultInventory;
import com.veero.escaperoomgame.core.model.Player;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final DefaultInventory defaultInventory;
    private final PlayerRepository playerRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, PlayerRepository playerRepository) {
        this.defaultInventory = new DefaultInventory(inventoryRepository);
        this.playerRepository = playerRepository;
    }

    public InventoryResponse getEntireInventory(String playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new IllegalArgumentException("No inventory linked to player with ID: " + playerId);
        }

        return defaultInventory.getEntireInventory(inventoryId);
    }

    public boolean addItemToInventory(String playerId, Item item) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));

        String inventoryId = player.getInventoryId();
        if (inventoryId == null) {
            throw new IllegalArgumentException("No inventory linked to player with ID: " + playerId);
        }

        defaultInventory.addItem(inventoryId, item);
        return true;
    }
}

