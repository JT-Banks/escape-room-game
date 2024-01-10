package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.GameObject;
import com.veero.escaperoomgame.asylum.repository.GameObjectRepository;
import com.veero.escaperoomgame.core.dto.ActionResponse;
import com.veero.escaperoomgame.core.dto.GameObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private final GameObjectRepository gameObjectRepository;

    @Autowired
    public ActionService(GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }

    public ActionResponse performAction(String roomId, String interactionId, String actionType) {
        //Logic to handle the action
        //looking up the room and object, and then performing the action
        //returning the response

        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new IllegalArgumentException("Game object not found"));

        Action selectedAction = gameObject.getActions().stream()
                .filter(a -> a.getActionType().equalsIgnoreCase(actionType)).findFirst().orElseThrow(()
                -> new IllegalArgumentException("Action not found for: " + actionType));

        String result = executeAction(selectedAction);

        return new ActionResponse(true, result);
    }

    public GameObjectResponse getObjectDetails(String roomId, String interactionId) {
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new IllegalArgumentException("Game object not found"));

        return new GameObjectResponse(gameObject.getName(), gameObject.getRoomId(), gameObject.getInteractionId(), gameObject.getDescription());
    }

    private String executeAction(Action action) {
        return action.getResult();

    }
}
