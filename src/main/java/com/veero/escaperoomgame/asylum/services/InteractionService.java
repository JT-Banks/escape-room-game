package com.veero.escaperoomgame.asylum.services;

import com.veero.escaperoomgame.generated.model.InteractionResponse;
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

        if (gameObject.getActions() != null) {
            interactionResponse.setActions(
                gameObject.getActions().stream()
                    .map(this::convertAction)
                    .toList()
            );
        }

        interactionResponse.setRelatedObjects(gameObject.getRelatedObjects());
        return interactionResponse;
    }

    private com.veero.escaperoomgame.generated.model.Action convertAction(com.veero.escaperoomgame.asylum.model.Action asylumAction) {
        com.veero.escaperoomgame.generated.model.Action generatedAction = new com.veero.escaperoomgame.generated.model.Action();
        generatedAction.setActionType(asylumAction.getActionType() != null ? asylumAction.getActionType().toString() : null);
        generatedAction.setResult(asylumAction.getResult());
        return generatedAction;
    }
}