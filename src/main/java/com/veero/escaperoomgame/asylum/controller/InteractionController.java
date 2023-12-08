package com.veero.escaperoomgame.asylum.controller;

import com.veero.escaperoomgame.asylum.dto.InteractionRequest;
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

         @GetMapping("/interact/{id}")
         public ResponseEntity<String> interactWithItem(@PathVariable String id) {
             String description = interactionService.interactWith(id);
             return ResponseEntity.ok(description);

         }
}
