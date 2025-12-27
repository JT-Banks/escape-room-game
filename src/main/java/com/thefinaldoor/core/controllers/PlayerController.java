package com.thefinaldoor.core.controllers;

import com.thefinaldoor.core.repositories.PlayerRepository;
import com.thefinaldoor.core.services.PlayerService;
import com.thefinaldoor.generated.model.PlayerCreationResponse;
import com.thefinaldoor.core.models.Player;
import com.thefinaldoor.generated.model.PlayerCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    @Autowired
    public PlayerController(
            PlayerRepository playerRepository,
            PlayerService playerService
    ) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerCreationResponse> createPlayer(@RequestBody PlayerCreateRequest newPlayerData) {
        Player newPlayer = playerService.createNewPlayer(newPlayerData);
        playerRepository.save(newPlayer);
        PlayerCreationResponse response = playerService.createPlayerResponse(newPlayer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable String playerId) {
        Player player = playerRepository.findByPlayerId(playerId).orElseThrow(()
                -> new RuntimeException("Player not found"));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
