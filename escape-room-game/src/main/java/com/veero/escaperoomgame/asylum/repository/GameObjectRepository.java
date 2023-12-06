package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.asylum.model.GameObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameObjectRepository extends MongoRepository<GameObject, String> {
        List<GameObject> findByInteractionId(String id);
}
