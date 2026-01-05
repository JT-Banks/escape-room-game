package com.thefinaldoor.core.services;

import com.thefinaldoor.asylum.model.Item;
import com.thefinaldoor.asylum.services.StarterItemService;
import com.thefinaldoor.core.exceptions.DuplicatePlayerNameException;
import com.thefinaldoor.core.exceptions.InventoryNotFoundException;
import com.thefinaldoor.core.exceptions.PlayerNotFoundException;
import com.thefinaldoor.core.models.Inventory;
import com.thefinaldoor.generated.model.PlayerCreationResponse;
import com.thefinaldoor.generated.model.PlayerCreateRequest;
import com.thefinaldoor.generated.model.InventoryDto;
import com.thefinaldoor.generated.model.Action;
import com.thefinaldoor.core.models.Player;
import com.thefinaldoor.core.repositories.InventoryRepository;
import com.thefinaldoor.core.repositories.PlayerRepository;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.thefinaldoor.asylum.constants.AsylumConstants.ANNIE_ROOM_ID;

@Service
public class PlayerService {

    private final StarterItemService starterItemService;
    private final InventoryRepository inventoryRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(
            StarterItemService starterItemService,
            InventoryRepository inventoryRepository,
            PlayerRepository playerRepository
    ) {
        this.starterItemService = starterItemService;
        this.inventoryRepository = inventoryRepository;
        this.playerRepository = playerRepository;
    }

    public Player createNewPlayer(PlayerCreateRequest newPlayerData) {
        // Check if player name already exists
        playerRepository.findByPlayerName(newPlayerData.getPlayerName())
                .ifPresent(existingPlayer -> {
                    throw new DuplicatePlayerNameException(newPlayerData.getPlayerName());
                });

        Player newPlayer = new Player();
        List<Action> actionList = new ArrayList<>();
        Action inspectAction = new Action();
        inspectAction.setActionType("INSPECT");
        actionList.add(inspectAction);

        newPlayer.setPlayerName(newPlayerData.getPlayerName());
        newPlayer.setPlayerId(UUID.randomUUID().toString());
        newPlayer.setBackground(newPlayerData.getBackground());
        newPlayer.setDifficultyLevel(newPlayerData.getDifficultyLevel().getValue());
        newPlayer.setSpecialAbility(newPlayerData.getSpecialAbility());
        newPlayer.setStatus(Player.PlayerStatus.PLAYING);
        newPlayer.setCurrentRoomId(ANNIE_ROOM_ID);
        newPlayer.setInteractionId("1");
        newPlayer.setScore(0);
        newPlayer.setTimeRemaining(60.00);

        Item starterItem = starterItemService.getStarterItem(newPlayerData.getStarterItem());
        newPlayer.setStarterItem(starterItem.getName());
        newPlayer.setActions(actionList);

        Inventory inventory = new Inventory();
        inventory.setPlayerId(newPlayer.getPlayerId());
        inventory.addItem(starterItem.getId(), starterItem.getName(),
                starterItem.getDescription(), starterItem.getType(), starterItem.getUse());
        inventoryRepository.save(inventory);

        newPlayer.linkInventory(inventory.getPlayerId());

        return newPlayer;
    }

    public PlayerCreationResponse createPlayerResponse(Player newPlayer) {
        Inventory inventory = inventoryRepository.findById(newPlayer.getInventoryId())
                .orElseThrow(() -> new InventoryNotFoundException(newPlayer.getInventoryId()));

        boolean success = inventory.getItems() != null && !inventory.getItems().isEmpty();

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(inventory.getPlayerId());
        inventoryDto.setPlayerId(inventory.getPlayerId());
        inventoryDto.setItems(
            inventory.getItems().stream()
                .map(this::convertToGeneratedItem)
                .toList()
        );

        return getPlayerCreationResponse(newPlayer, inventoryDto, success);
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

    @Nonnull
    private static PlayerCreationResponse getPlayerCreationResponse(Player newPlayer, InventoryDto inventoryDto, boolean success) {
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
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public Player getPlayerByName(String playerName) {
        return playerRepository.findByPlayerName(playerName)
                .orElseThrow(() -> new PlayerNotFoundException(playerName));
    }
}

