package com.veero.escaperoomgame.asylum.controllers;

import com.veero.escaperoomgame.asylum.dto.InteractionResponse;
import com.veero.escaperoomgame.asylum.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InteractionController {

         @Autowired
         private InteractionService interactionService;

         @Autowired
         public InteractionController(InteractionService interactionService) {
             this.interactionService = interactionService;
         }

         @GetMapping("/interact/{interactionId}")
         public ResponseEntity<InteractionResponse> interact(@PathVariable String interactionId) {
             InteractionResponse response = interactionService.interactWith(interactionId);
             return ResponseEntity.ok(response);

         }
}
