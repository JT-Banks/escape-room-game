package com.thefinaldoor.core.repositories;

import com.thefinaldoor.core.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findByPlayerId(String playerId);

    Optional<Player> findByPlayerName(String playerName);

    Player save(Player player);
}

