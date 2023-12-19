package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.asylum.model.GameObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GameObjectRepository extends MongoRepository<GameObject, String> {
        List<GameObject> findByInteractionId(String id);
        Optional<GameObject> findByRoomIdAndInteractionId(String id, String interactionId);
}
