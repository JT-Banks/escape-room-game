package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.model.Room;
import com.veero.escaperoomgame.asylum.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
}
