package com.thefinaldoor.asylum.controllers;

import com.thefinaldoor.asylum.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<com.thefinaldoor.generated.model.Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
}
