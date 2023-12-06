package com.veero.escaperoomgame.core.service;

import com.veero.escaperoomgame.asylum.model.GameObject;
import com.veero.escaperoomgame.asylum.model.Room;
import org.springframework.stereotype.Service;

import static com.veero.escaperoomgame.core.constants.*;

@Service
public class DescriptionService {

    public String getDescription(GameObject gameObject, Room room) {
        if (DOOR.equalsIgnoreCase(gameObject.getInteractionId())) {
            if (ANNIES_ROOM.equalsIgnoreCase(room.getName())) {
                return "The door is locked. It locks from the other side.";
            } else if (BATHROOM.equalsIgnoreCase(room.getName())) {
                return "The Janitor's closet is locked. There must be a key somewhere...";
            }
        }
        return "No specific description found.";
    }
}
