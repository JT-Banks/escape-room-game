package com.thefinaldoor.core.services;

import com.thefinaldoor.asylum.model.Action;
import com.thefinaldoor.asylum.model.GameObject;
import com.thefinaldoor.asylum.repositories.GameObjectRepository;
import com.thefinaldoor.generated.model.ActionResponse;
import com.thefinaldoor.generated.model.GameObjectResponse;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private final GameObjectRepository gameObjectRepository;

    public ActionService(GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }

    public ActionResponse performAction(String roomId, String interactionId, String actionType) {
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new IllegalArgumentException("Game object not found in room " + roomId + " with interaction ID " + interactionId));

        Action selectedAction = gameObject.getActions().stream()
                .filter(a -> a.getActionType().name().equalsIgnoreCase(actionType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Action '" + actionType + "' not available for this object"));

        String result = executeAction(selectedAction);

        ActionResponse response = new ActionResponse();
        response.setSuccess(true);
        response.setResult(result);
        return response;
    }

    public GameObjectResponse getObjectDetails(String roomId, String interactionId) {
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new IllegalArgumentException("Game object not found in room " + roomId + " with interaction ID " + interactionId));

        GameObjectResponse response = new GameObjectResponse();
        response.setName(gameObject.getName());
        response.setRoomId(gameObject.getRoomId());
        response.setInteractionId(gameObject.getInteractionId());
        response.setDescription(gameObject.getDescription());
        return response;
    }

    private String executeAction(Action action) {
        return action.getResult();
    }
}

