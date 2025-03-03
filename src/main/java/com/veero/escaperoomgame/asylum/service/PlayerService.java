package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.InteractionType;
import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.InventoryImpl;
import com.veero.escaperoomgame.core.model.Player;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.veero.escaperoomgame.asylum.constants.AsylumConstants.ASYLUM_ROOM_ID;

@Service
public class PlayerService {

    private final StarterItemService starterItemService;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public PlayerService(StarterItemService starterItemService, InventoryRepository inventoryRepository) {
        this.starterItemService = starterItemService;
        this.inventoryRepository = inventoryRepository;
    }

    public Player createNewPlayer(Player newPlayerData) {
        // Initialize Player
        Player newPlayer = new Player();
        List<Action> actionList = new ArrayList<>();
        Action inspectAction = new Action();
        inspectAction.setActionType(InteractionType.INSPECT);
        actionList.add(inspectAction);
        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(UUID.randomUUID().toString());
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId(ASYLUM_ROOM_ID);
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.00);

        // Set starter item
        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.setActions(actionList);

        // Create and save Inventory
        Inventory inventory = new Inventory();
        inventory.setPlayerId(newPlayer.getPlayerId());
        inventory.addItem(starterItem.getId(), starterItem.getItemId(), starterItem.getName(),
                starterItem.getDescription(), starterItem.getType(), starterItem.getUse());
        inventoryRepository.save(inventory);

        // Link Inventory to Player
        newPlayer.linkInventory(inventory.getPlayerId());

        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
        Inventory inventory = inventoryRepository.findById(newPlayer.getInventoryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for player with ID: " + newPlayer.getPlayerId()));
        boolean success = !inventory.getItems().isEmpty();

        return new PlayerCreationResponse(
                newPlayer.getPlayerId(),
                newPlayer.getPlayerName(),
                newPlayer.getBackground(),
                newPlayer.getDifficultyLevel(),
                newPlayer.getSpecialAbility(),
                newPlayer.getStarterItem(),
                inventory, // Pass the fetched inventory0
                success
        );
    }

}

