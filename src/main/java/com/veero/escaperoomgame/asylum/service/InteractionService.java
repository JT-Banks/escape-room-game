package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.GameObject;
import com.veero.escaperoomgame.asylum.model.Room;
import com.veero.escaperoomgame.asylum.repository.GameObjectRepository;
import com.veero.escaperoomgame.asylum.repository.RoomRepository;
import com.veero.escaperoomgame.core.service.DescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {

    @Autowired
    private GameObjectRepository gameObjectRepository;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private RoomRepository roomRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String interactWith(String id) {

        log.info("Searching for object with ID: " + id);

        List<GameObject> gameObjects = gameObjectRepository.findByInteractionId(id);

        if (gameObjects.isEmpty()) {
            log.warn("No object found with interactionId: " + id);
            return "No object found with interactionId: " + id;
        }
        GameObject gameObject = gameObjects.get(0);
        Optional<Room> roomOptional = roomRepository.findById(gameObject.getRoomId());

        if (roomOptional.isEmpty()) {
            return "No room found for this object.";
        }
        Room room = roomOptional.get();
        return descriptionService.getDescription(gameObject, room);
    }
}