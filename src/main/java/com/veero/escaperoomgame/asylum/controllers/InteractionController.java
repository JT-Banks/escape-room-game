package com.veero.escaperoomgame.asylum.controllers;

import com.veero.escaperoomgame.generated.model.InteractionResponse;
import com.veero.escaperoomgame.asylum.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping("/{interactionId}")
    public ResponseEntity<InteractionResponse> interact(@PathVariable String interactionId) {
        InteractionResponse response = interactionService.interactWith(interactionId);
        return ResponseEntity.ok(response);
    }
}
