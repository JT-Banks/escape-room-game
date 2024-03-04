package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.model.Player;
import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private PlayerRepository playerRepository;

    public boolean addItem(String playerId, String itemId) {
        Optional<Player> playerOptional = playerRepository.findByPlayerId(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            Inventory inventory = new Inventory();
            player.getInventory().add(inventory);
            playerRepository.save(player);
            return true;
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
    }

    public boolean removeItem(String playerId, String itemId) {
        Optional<Player> playerOptional = playerRepository.findByPlayerId(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            Inventory inventory = new Inventory();
            player.getInventory().remove(inventory);
            playerRepository.save(player);
            return true;
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
    }

    public List<Inventory> getEntireInventory(String playerId) {
        Optional<Player> playerInventory = playerRepository.findByPlayerId(playerId);
        if (playerInventory.isPresent()) {
            return playerInventory.get().getInventory();
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId + "!");
        }
    }
}
