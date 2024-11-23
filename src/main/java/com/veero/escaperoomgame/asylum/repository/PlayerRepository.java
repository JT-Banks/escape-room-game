package com.veero.escaperoomgame.asylum.repository;

import com.veero.escaperoomgame.core.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findByPlayerId(String playerId);

    //Create a new player
    Player save(Player player);
}
