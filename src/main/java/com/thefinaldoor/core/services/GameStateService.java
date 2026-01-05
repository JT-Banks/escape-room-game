package com.thefinaldoor.core.services;

import com.thefinaldoor.core.exceptions.PlayerNotFoundException;
import com.thefinaldoor.core.models.Player;
import com.thefinaldoor.core.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class GameStateService {

    private final PlayerRepository playerRepository;

    private final Map<String, Set<String>> playerDiscoveredClues = new HashMap<>();
    private final Map<String, Set<String>> playerCompletedPuzzles = new HashMap<>();
    private final Map<String, Boolean> playerVentStatus = new HashMap<>();

    public GameStateService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addDiscoveredClue(String playerId, String clueId) {
        playerDiscoveredClues
            .computeIfAbsent(playerId, k -> new HashSet<>())
            .add(clueId);
    }

    public Set<String> getDiscoveredClues(String playerId) {
        return playerDiscoveredClues.getOrDefault(playerId, new HashSet<>());
    }

    public boolean hasDiscoveredAllClues(String playerId, Set<String> requiredClues) {
        Set<String> discovered = getDiscoveredClues(playerId);
        return discovered.containsAll(requiredClues);
    }

    public int getClueCount(String playerId) {
        return getDiscoveredClues(playerId).size();
    }

    public void markPuzzleCompleted(String playerId, String puzzleId) {
        playerCompletedPuzzles
            .computeIfAbsent(playerId, k -> new HashSet<>())
            .add(puzzleId);
    }

    public boolean isPuzzleCompleted(String playerId, String puzzleId) {
        return playerCompletedPuzzles
            .getOrDefault(playerId, new HashSet<>())
            .contains(puzzleId);
    }

    public void setVentOpened(String playerId, boolean opened) {
        playerVentStatus.put(playerId, opened);
    }

    public boolean isVentOpened(String playerId) {
        return playerVentStatus.getOrDefault(playerId, false);
    }

    public void movePlayerToRoom(String playerId, String roomId) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        player.setCurrentRoomId(roomId);
        playerRepository.save(player);
    }

    public String getCurrentRoom(String playerId) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        return player.getCurrentRoomId();
    }

    public Map<String, Object> getPlayerProgress(String playerId) {
        Map<String, Object> progress = new HashMap<>();
        progress.put("discoveredClues", getDiscoveredClues(playerId));
        progress.put("clueCount", getClueCount(playerId));
        progress.put("completedPuzzles", playerCompletedPuzzles.getOrDefault(playerId, new HashSet<>()));
        progress.put("ventOpened", isVentOpened(playerId));
        progress.put("currentRoom", getCurrentRoom(playerId));
        return progress;
    }

    public void resetPlayerProgress(String playerId) {
        playerDiscoveredClues.remove(playerId);
        playerCompletedPuzzles.remove(playerId);
        playerVentStatus.remove(playerId);
    }
}

