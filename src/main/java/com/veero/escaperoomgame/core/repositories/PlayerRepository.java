package com.veero.escaperoomgame.core.repositories;

import com.veero.escaperoomgame.core.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findByPlayerId(String playerId);

    Player save(Player player);
}

