package com.veero.escaperoomgame.asylum.controllers;

import com.veero.escaperoomgame.asylum.model.Room;
import com.veero.escaperoomgame.asylum.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("unused")
public class RoomController {

   @Autowired
   private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomService.getAllRooms();
    }

}
