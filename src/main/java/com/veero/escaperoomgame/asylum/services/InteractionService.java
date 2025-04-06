package com.veero.escaperoomgame.asylum.services;

import com.veero.escaperoomgame.asylum.dto.InteractionResponse;
import com.veero.escaperoomgame.asylum.model.GameObject;
import com.veero.escaperoomgame.asylum.repositories.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionService {

    @Autowired
    private GameObjectRepository gameObjectRepository;

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