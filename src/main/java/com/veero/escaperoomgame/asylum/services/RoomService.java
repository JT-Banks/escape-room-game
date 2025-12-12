package com.veero.escaperoomgame.asylum.services;

import com.veero.escaperoomgame.asylum.model.Room;
import com.veero.escaperoomgame.asylum.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    //Always start in starter room
    public Room getStartingRoom() {
        return roomRepository.findById("1")
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }
}
