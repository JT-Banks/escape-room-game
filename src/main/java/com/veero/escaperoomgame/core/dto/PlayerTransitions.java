package com.veero.escaperoomgame.core.dto;

import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PlayerTransitions {

    @Autowired
    private PlayerRepository playerRepository;

    public void updatePlayerRoom(String playerId, String newRoomId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.setCurrentRoomId(newRoomId);
            playerRepository.save(player);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

}
