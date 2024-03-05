package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private StarterItemService starterItemService;

    public Player createNewPlayer(Player newPlayerData) {
        // Logic to create a new player
        // Similar to your initializePlayer method
        Player newPlayer = new Player();
        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(String.valueOf((int) (Math.random() * 9000) + 1000));
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        //Need to set the player's current room and interaction to the first room and interaction
        //First room is 'Annie's Room' = "1", second room = "2" etc.
        newPlayer.setCurrentRoomId("1");
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.0);

        // Initialize the player's inventory with the starter item
        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.addItemToInventory(starterItem); // Using the new method in Player

        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
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

