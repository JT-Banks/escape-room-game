package com.thefinaldoor.core.services;

import com.thefinaldoor.core.exceptions.InvalidRequestException;
import com.thefinaldoor.generated.model.Action;
import com.thefinaldoor.asylum.model.GameObject;
import com.thefinaldoor.asylum.repositories.GameObjectRepository;
import com.thefinaldoor.generated.model.ActionResponse;
import com.thefinaldoor.generated.model.GameObjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    private final GameObjectRepository gameObjectRepository;
    private final GameStateService gameStateService;
    private final InventoryService inventoryService;

    public ActionService(
            GameObjectRepository gameObjectRepository,
            GameStateService gameStateService,
            InventoryService inventoryService) {
        this.gameObjectRepository = gameObjectRepository;
        this.gameStateService = gameStateService;
        this.inventoryService = inventoryService;
    }

    public ActionResponse performAction(String playerId, String roomId, String interactionId, String actionType) {
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new InvalidRequestException(
                        "Game object not found in room " + roomId + " with interaction ID " + interactionId)
                );

        Action selectedAction = gameObject.getActions().stream()
                .filter(a -> a.getActionType().equalsIgnoreCase(actionType))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException(
                        "Action '" + actionType + "' not available for this object")
                );

        if (requiresItem(interactionId)) {
            String requiredItem = getRequiredItem(interactionId);
            if (!playerHasItem(playerId, requiredItem)) {
                ActionResponse response = new ActionResponse();
                response.setSuccess(false);
                response.setResult("You need a " + requiredItem + " to do that.");
                return response;
            }
        }

        String result = executeAction(playerId, interactionId, actionType, selectedAction);

        trackClueDiscovery(playerId, interactionId, actionType);

        ActionResponse response = new ActionResponse();
        response.setSuccess(true);
        response.setResult(result);
        return response;
    }

    public GameObjectResponse getObjectDetails(String roomId, String interactionId) {
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new InvalidRequestException(
                        "Game object not found in room " + roomId + " with interaction ID " + interactionId)
                );

        GameObjectResponse response = new GameObjectResponse();
        response.setName(gameObject.getName());
        response.setRoomId(gameObject.getRoomId());
        response.setInteractionId(gameObject.getInteractionId());
        response.setDescription(gameObject.getDescription());
        return response;
    }

    public List<GameObject> getRoomObjects(String roomId) {
        return gameObjectRepository.findAll().stream()
                .filter(obj -> obj.getRoomId().equals(roomId))
                .toList();
    }

    private String executeAction(String playerId, String interactionId, String actionType, Action action) {
        String result = action.getResult();

        if (interactionId.equals("vent") && actionType.equalsIgnoreCase("use")) {
            gameStateService.setVentOpened(playerId, true);
            result += "\n\nYou've opened the vent! You can now escape through it.";
        }

        if (interactionId.equals("desk") && actionType.equalsIgnoreCase("open")) {
            gameStateService.addDiscoveredClue(playerId, "annies-letters");
        }

        if (interactionId.equals("bed") && actionType.equalsIgnoreCase("move")) {
            gameStateService.addDiscoveredClue(playerId, "burnt-drawing");
        }

        return result;
    }

    private void trackClueDiscovery(String playerId, String interactionId, String actionType) {
        if (actionType.equalsIgnoreCase("inspect")) {
            switch (interactionId) {
                case "charredWall" -> gameStateService.addDiscoveredClue(playerId, "charred-wall");
                case "teddyBear" -> gameStateService.addDiscoveredClue(playerId, "scratcher-bear");
                case "clawMarks" -> gameStateService.addDiscoveredClue(playerId, "claw-marks");
                case "burntDrawing" -> gameStateService.addDiscoveredClue(playerId, "burnt-drawing");
            }
        }
    }

    private boolean requiresItem(String interactionId) {
        return interactionId.equals("vent");
    }

    private String getRequiredItem(String interactionId) {
        if (interactionId.equals("vent")) {
            return "screwdriver";
        }
        return null;
    }

    private boolean playerHasItem(String playerId, String itemId) {
        try {
            var inventory = inventoryService.getEntireInventory(playerId);
            return inventory.getItems() != null &&
                   inventory.getItems().stream()
                       .anyMatch(item -> item.getId().equals(itemId));
        } catch (Exception e) {
            return false;
        }
    }
}

