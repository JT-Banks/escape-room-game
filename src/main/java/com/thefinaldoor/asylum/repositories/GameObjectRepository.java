package com.thefinaldoor.asylum.repositories;

import com.thefinaldoor.asylum.model.GameObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GameObjectRepository extends MongoRepository<GameObject, String> {
        List<GameObject> findByInteractionId(String id);
        Optional<GameObject> findByRoomIdAndInteractionId(String id, String interactionId);
}
