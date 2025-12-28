package com.thefinaldoor.core.controllers;

import com.thefinaldoor.generated.model.ActionRequest;
import com.thefinaldoor.generated.model.ActionResponse;
import com.thefinaldoor.generated.model.GameObjectResponse;
import com.thefinaldoor.core.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms/{roomId}/objects/{objectId}/actions")
public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping
    public ResponseEntity<ActionResponse> performAction(
            @PathVariable String roomId,
            @PathVariable String objectId,
            @RequestBody ActionRequest request
    ) {
        ActionResponse response = actionService.performAction(roomId, objectId, request.getActionType().getValue());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GameObjectResponse> getObjectDetails(
            @PathVariable String roomId,
            @PathVariable String objectId
    ) {
        GameObjectResponse response = actionService.getObjectDetails(roomId, objectId);
        return ResponseEntity.ok(response);
    }
}
