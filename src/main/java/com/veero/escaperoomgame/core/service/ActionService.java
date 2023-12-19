package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.model.Action;
import com.veero.escaperoomgame.asylum.model.GameObject;
import com.veero.escaperoomgame.asylum.repository.GameObjectRepository;
import com.veero.escaperoomgame.core.dto.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Autowired
    private GameObjectRepository gameObjectRepository;

    public ActionResponse performAction(String roomId, String interactionId, String actionType) {
        //Logic to handle the action
        //looking up the room and object, and then performing the action
        //returning the response
        GameObject gameObject = gameObjectRepository.findByRoomIdAndInteractionId(roomId, interactionId)
                .orElseThrow(() -> new IllegalArgumentException("Game object not found"));

        Action selectedAction = gameObject.getActions().stream()
                .filter(a -> a.getActionType().equals(actionType)).findFirst().orElseThrow(()
                -> new IllegalArgumentException("Action not found"));

        String result = executeAction(selectedAction);

        return new ActionResponse(true, result);
    }

    private String executeAction(Action action) {
        return action.getResult();

    }
}
