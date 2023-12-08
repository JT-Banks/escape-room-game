package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.dto.InteractionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @PostMapping("/interact")
    public ResponseEntity<String> interactWithRoom(@RequestBody InteractionRequest request) {
        return ResponseEntity.ok("Interacted with room");
    }
}
