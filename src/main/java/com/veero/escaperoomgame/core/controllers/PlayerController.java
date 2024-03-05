package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.repository.PlayerRepository;
import com.veero.escaperoomgame.asylum.service.PlayerService;
import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.dto.PlayerCreationResponse;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@SuppressWarnings("unused")
public class PlayerController {

    //TODO: Add all the logic for items

    private final PlayerRepository playerRepository;

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/create")
    public ResponseEntity<PlayerCreationResponse> createPlayer(@RequestBody Player newPlayerData) {
        Player newPlayer = playerService.createNewPlayer(newPlayerData);
        PlayerCreationResponse response = playerService.createPlayerResponse(newPlayer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable String playerId) {
        Player player = playerRepository.findByPlayerId(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
