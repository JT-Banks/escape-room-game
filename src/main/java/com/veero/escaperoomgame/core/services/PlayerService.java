package com.veero.escaperoomgame.core.services;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.InteractionType;
import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.services.StarterItemService;
import com.veero.escaperoomgame.core.models.Inventory;
import com.veero.escaperoomgame.generated.model.PlayerCreationResponse;
import com.veero.escaperoomgame.generated.model.PlayerCreateRequest;
import com.veero.escaperoomgame.generated.model.InventoryDto;
import com.veero.escaperoomgame.core.models.Player;
import com.veero.escaperoomgame.core.repositories.InventoryRepository;
import com.veero.escaperoomgame.core.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.veero.escaperoomgame.asylum.constants.AsylumConstants.ASYLUM_ROOM_ID;

@Service
public class PlayerService {

    private final StarterItemService starterItemService;
    private final InventoryRepository inventoryRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(StarterItemService starterItemService, InventoryRepository inventoryRepository, PlayerRepository playerRepository) {
        this.starterItemService = starterItemService;
        this.inventoryRepository = inventoryRepository;
        this.playerRepository = playerRepository;
    }

    public Player createNewPlayer(PlayerCreateRequest newPlayerData) {
        Player newPlayer = new Player();
        List<Action> actionList = new ArrayList<>();
        Action inspectAction = new Action();
        inspectAction.setActionType(InteractionType.INSPECT);
        actionList.add(inspectAction);

        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(UUID.randomUUID().toString());
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel().getValue());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId(ASYLUM_ROOM_ID);
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.00);

        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.setActions(actionList);

        Inventory inventory = new Inventory();
        inventory.setPlayerId(newPlayer.getPlayerId());
        inventory.addItem(starterItem.getId(), starterItem.getItemId(), starterItem.getName(),
                starterItem.getDescription(), starterItem.getType(), starterItem.getUse());
        inventoryRepository.save(inventory);

        newPlayer.linkInventory(inventory.getPlayerId());

        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
        Inventory inventory = inventoryRepository.findById(newPlayer.getInventoryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for player with ID: " + newPlayer.getPlayerId()));

        boolean success = inventory.getItems() != null && !inventory.getItems().isEmpty();

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(inventory.getPlayerId());
        inventoryDto.setPlayerId(inventory.getPlayerId());

        PlayerCreationResponse response = new PlayerCreationResponse();
        response.setPlayerId(newPlayer.getPlayerId());
        response.setPlayerName(newPlayer.getPlayerName());
        response.setBackground(newPlayer.getBackground());
        response.setDifficulty(newPlayer.getDifficultyLevel());
        response.setSpecialAbility(newPlayer.getSpecialAbility());
        response.setStarterItem(newPlayer.getStarterItem());
        response.setInventory(inventoryDto);
        response.setSuccess(success);

        return response;
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));
    }
}

