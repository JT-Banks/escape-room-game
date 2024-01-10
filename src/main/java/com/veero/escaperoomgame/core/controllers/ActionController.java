package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.core.dto.ActionRequest;
import com.veero.escaperoomgame.core.dto.ActionResponse;
import com.veero.escaperoomgame.core.dto.GameObjectResponse;
import com.veero.escaperoomgame.core.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action")
public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/{roomId}/{interactionId}")
    public ResponseEntity<?> performAction(@PathVariable String roomId,
                                           @PathVariable String interactionId,
                                           @RequestBody ActionRequest request) {
        ActionResponse response = actionService.performAction(roomId, interactionId, request.getActionType());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{roomId}/{interactionId}")
    public ResponseEntity<GameObjectResponse> getObjectDetails(@PathVariable String roomId,
                                                               @PathVariable String interactionId) {
        GameObjectResponse response = actionService.getObjectDetails(roomId, interactionId);
        return ResponseEntity.ok(response);
    }
}
