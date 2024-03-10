package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.core.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    //TODO: CREATE DOCUMENT IN MONGODB FOR THIS
    Optional<Player> findByPlayerId(String playerId);

    //Create a new player
    Player save(Player player);

    //Save item to inventory
    Player saveItem(Player player);
}
