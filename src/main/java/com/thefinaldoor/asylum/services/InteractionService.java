package com.thefinaldoor.asylum.services;

import com.thefinaldoor.generated.model.InteractionResponse;
import com.thefinaldoor.asylum.model.GameObject;
import com.thefinaldoor.asylum.repositories.GameObjectRepository;
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