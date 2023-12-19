package com.veero.escaperoomgame.asylum.model;

import com.veero.escaperoomgame.asylum.repository.GameObjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Data
public class Player {
    private String id;
    private String name;
    private String currentRoomId;
    private List<Item> inventory;
    private List<Action> actions;

    @Autowired
    GameObjectRepository gameObjectRepository;

    public String performAction(String objectId, String actionType) {
        GameObject gameObject = gameObjectRepository.findById(objectId).orElse(null);
        if (gameObject == null) {
            return "Object not found";
        }
        Optional<Action> action = gameObject.getActions().stream()
                .filter(a -> a.getActionType().equals(actionType)).findFirst();
        return action.map(Action::getResult).orElse("Action not available");
    }
}
