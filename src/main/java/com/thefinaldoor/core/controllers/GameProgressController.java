package com.thefinaldoor.core.controllers;

import com.thefinaldoor.core.services.GameStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/players/{playerId}/progress")
public class GameProgressController {

    private final GameStateService gameStateService;

    public GameProgressController(GameStateService gameStateService) {
        this.gameStateService = gameStateService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProgress(@PathVariable String playerId) {
        Map<String, Object> progress = gameStateService.getPlayerProgress(playerId);
        return ResponseEntity.ok(progress);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetProgress(@PathVariable String playerId) {
        Map<String, Object> progress = gameStateService.getPlayerProgress(playerId);
        if (progress == null || progress.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gameStateService.resetPlayerProgress(playerId);
        return ResponseEntity.ok("Player progress reset successfully");
    }

    @GetMapping("/room")
    public ResponseEntity<String> getCurrentRoom(@PathVariable String playerId) {
        String currentRoom = gameStateService.getCurrentRoom(playerId);
        return ResponseEntity.ok(currentRoom);
    }

    @PostMapping("/room/{roomId}")
    public ResponseEntity<String> moveToRoom(
            @PathVariable String playerId,
            @PathVariable String roomId) {
        gameStateService.movePlayerToRoom(playerId, roomId);
        return ResponseEntity.ok("Player moved to room " + roomId);
    }
}

