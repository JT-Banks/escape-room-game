package com.thefinaldoor.asylum.services;

import com.thefinaldoor.asylum.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<com.thefinaldoor.generated.model.Room> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToGeneratedRoom)
                .collect(Collectors.toList());
    }

    public com.thefinaldoor.asylum.model.Room getStartingRoom() {
        return roomRepository.findById("1")
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    private com.thefinaldoor.generated.model.Room convertToGeneratedRoom(com.thefinaldoor.asylum.model.Room asylumRoom) {
        com.thefinaldoor.generated.model.Room generatedRoom = new com.thefinaldoor.generated.model.Room();
        generatedRoom.setId(asylumRoom.getId());
        generatedRoom.setName(asylumRoom.getName());
        generatedRoom.setDescription(asylumRoom.getDescription());
        generatedRoom.setIsLocked(asylumRoom.isLocked());
        generatedRoom.setItems(asylumRoom.getItems());
        generatedRoom.setPuzzles(asylumRoom.getPuzzles());
        generatedRoom.setHints(asylumRoom.getHints());
        return generatedRoom;
    }
}
