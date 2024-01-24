package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import com.veero.escaperoomgame.core.dto.InventoryItem;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InventoryItem inventoryItems;

    @PostMapping("/create")
    public ResponseEntity<PlayerCreationResponse> createPlayer(@RequestBody Player newPlayerData) {
        Player newPlayer = initializePlayer(newPlayerData);
        PlayerCreationResponse response = getPlayerCreationResponse(newPlayer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable String playerId) {
        Player player = playerRepository.findByPlayerId(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    private Player initializePlayer(Player newPlayerData) {
        Player newPlayer = new Player();
        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.0);
        createInventoryItem(newPlayerData.getStarterItem());
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    private InventoryItem createInventoryItem(String starterItem) {
        InventoryItem inventoryItems = new InventoryItem();
        inventoryItems.setItemId("1");
        inventoryItems.setItemName(starterItem);
        return inventoryItems;
    }

    private static PlayerCreationResponse getPlayerCreationResponse(Player newPlayer) {
        return new PlayerCreationResponse(
                newPlayer.getPlayerId(),
                newPlayer.getPlayerName(),
                newPlayer.getBackground(),
                newPlayer.getDifficultyLevel(),
                newPlayer.getSpecialAbility(),
                newPlayer.getStarterItem()
        );
    }
}
