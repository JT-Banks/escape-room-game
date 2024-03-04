package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.model.Player;
import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private PlayerRepository playerRepository;

    public boolean addItem(String playerId, Item item) {
        Optional<Player> playerOptional = playerRepository.findByPlayerId(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.getInventory().addItem(item);
            playerRepository.save(player);
            return true;
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
    }

    public boolean removeItem(String playerId, Item item) {
        Optional<Player> playerOptional = playerRepository.findByPlayerId(playerId);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.getInventory().removeItem(item);
            playerRepository.save(player);
            return true;
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
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
