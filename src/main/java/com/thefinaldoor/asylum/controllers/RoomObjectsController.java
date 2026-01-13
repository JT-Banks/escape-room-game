package com.thefinaldoor.asylum.controllers;

import com.thefinaldoor.asylum.model.GameObject;
import com.thefinaldoor.core.services.ActionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms/{roomId}/objects")
public class RoomObjectsController {

    private final ActionService actionService;

    public RoomObjectsController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping
    public ResponseEntity<List<ObjectSummary>> getRoomObjects(@PathVariable String roomId) {
        List<GameObject> objects = actionService.getRoomObjects(roomId);

        List<ObjectSummary> summaries = objects.stream()
                .map(obj -> new ObjectSummary(
                    obj.getId(),
                    obj.getName(),
                    obj.getInteractionId(),
                    obj.getDescription(),
                    obj.getInteractionType()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(summaries);
    }

    public record ObjectSummary(
        String id,
        String name,
        String interactionId,
        String description,
        String interactionType
    ) {}
}

