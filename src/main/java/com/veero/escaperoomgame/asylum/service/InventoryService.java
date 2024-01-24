package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.dto.Inventory;
import com.veero.escaperoomgame.asylum.model.Player;
import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private PlayerRepository playerRepository;

    public boolean addItem(String playerId, String itemId) {
        return true;
    }

    public boolean removeItem(String playerId, String itemId) {
        return true;
    }

    public Inventory getEntireInventory(String playerId) {
        Optional<Player> playerInventory = playerRepository.findByPlayerId(playerId);
        if (playerInventory.isPresent()) {
            return playerInventory.get().getInventory();
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId + "!");
        }
    }
}