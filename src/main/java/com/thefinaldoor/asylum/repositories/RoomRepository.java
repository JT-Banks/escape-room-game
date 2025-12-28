package com.thefinaldoor.asylum.repositories;

import com.thefinaldoor.asylum.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByName(String name);
}
