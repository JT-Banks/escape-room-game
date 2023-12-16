package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.core.dto.ActionResponse;
import com.veero.escaperoomgame.core.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/{roomId}/{interactionId}")
    public ResponseEntity<?> performAction(@PathVariable String roomId,
                                           @PathVariable String interactionId,
                                           @RequestBody String actionType) {
        ActionResponse response = actionService.performAction(roomId, interactionId, actionType);
        return ResponseEntity.ok(response);
    }
}
