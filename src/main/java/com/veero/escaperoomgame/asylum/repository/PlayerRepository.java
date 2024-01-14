package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.asylum.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    //TODO: CREATE DOCUMENT IN MONGODB FOR THIS
    Optional<Player> findByPlayerId(String playerId);
}
