package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@SuppressWarnings("unused")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private Inventory inventoryItems;

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
        //Need to generate a random player ID, between the numbers of 1000 and 9999
        newPlayer.setPlayerId(String.valueOf((int) (Math.random() * 9000) + 1000));
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId("1");
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.0);

        // Initialize the player's inventory with the starter item
        Item starterItem = createStarterItem(newPlayerData.getStarterItem());
        newPlayer.addItemToInventory(starterItem); // Using the new method in Player

        return newPlayer;
    }

    private Item createStarterItem(String starterItemName) {
        return new Item("starterItemID", starterItemName, "Description based on starter item", "Type", "Use");
    }

    private Inventory createInventoryItem(Player starterItem) {
        Inventory inventoryItems = new Inventory();
        Item flashlight = new Item("1", starterItem.getStarterItem() , "A flashlight to help you see in the dark", "1", "Illuminate darkness");
        inventoryItems.setItems(inventoryItems.getItems());
        inventoryItems.addItem(flashlight);
        return inventoryItems;
    }

    private static PlayerCreationResponse getPlayerCreationResponse(Player newPlayer) {
        return new PlayerCreationResponse(
                newPlayer.getPlayerId(),
                newPlayer.getPlayerName(),
                newPlayer.getBackground(),
                newPlayer.getDifficultyLevel(),
                newPlayer.getSpecialAbility(),
                newPlayer.getStarterItem(),
                newPlayer.getInventory()
        );
    }
}
