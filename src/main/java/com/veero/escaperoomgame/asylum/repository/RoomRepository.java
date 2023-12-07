package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.asylum.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByName(String name);
}
