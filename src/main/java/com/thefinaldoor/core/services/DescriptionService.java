package com.thefinaldoor.core.services;

import com.thefinaldoor.asylum.model.GameObject;
import com.thefinaldoor.asylum.model.Room;
import com.thefinaldoor.core.constants.Constants;
import org.springframework.stereotype.Service;

import static com.thefinaldoor.core.constants.Constants.*;

@Service
public class DescriptionService {

    public String getDescription(GameObject gameObject, Room room) {
        if (DOOR.equalsIgnoreCase(gameObject.getInteractionId())) {
            if (Constants.ANNIES_ROOM.equalsIgnoreCase(room.getName())) {
                return "The door is locked. It locks from the other side.";
            } else if (BATHROOM.equalsIgnoreCase(room.getName())) {
                return "The Janitor's closet is locked. There must be a key somewhere...";
            }
        }
        return "No specific description found.";
    }
}
