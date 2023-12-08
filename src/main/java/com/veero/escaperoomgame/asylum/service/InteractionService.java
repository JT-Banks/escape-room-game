package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.dto.InteractionResponse;
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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public InteractionResponse interactWith(String interactionId) {
        return gameObjectRepository.findByInteractionId(interactionId)
                .stream()
                .findFirst()
                .map(this::convertToInteractionResponse)
                .orElse(null);
    }

    private InteractionResponse convertToInteractionResponse(GameObject gameObject) {
        InteractionResponse interactionResponse = new InteractionResponse();
        interactionResponse.setName(gameObject.getName());
        interactionResponse.setDescription(gameObject.getDescription());
        interactionResponse.setClues(gameObject.getClues());
        interactionResponse.setActions(gameObject.getActions());
        interactionResponse.setRelatedObjects(gameObject.getRelatedObjects());
        return interactionResponse;
    }
}