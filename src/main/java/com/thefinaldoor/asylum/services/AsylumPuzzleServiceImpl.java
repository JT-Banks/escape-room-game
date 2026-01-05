package com.thefinaldoor.asylum.services;

import com.thefinaldoor.core.services.GameStateService;
import com.thefinaldoor.core.services.PuzzleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AsylumPuzzleServiceImpl implements PuzzleService {

    private final GameStateService gameStateService;

    public AsylumPuzzleServiceImpl(GameStateService gameStateService) {
        this.gameStateService = gameStateService;
    }

    public boolean solvePuzzle(String playerId, String roomId, String puzzleId, String solution) {
        return switch (puzzleId) {
            case "annies-story-puzzle" -> solveAnniesStoryPuzzle(playerId);
            case "vent-escape-puzzle" -> solveVentEscapePuzzle(playerId);
            default -> throw new IllegalArgumentException("Unknown puzzleId: " + puzzleId);
        };
    }

    @Override
    public boolean solvePuzzle(String roomId, String puzzleId, String solution) {
        throw new UnsupportedOperationException(
            "Use solvePuzzle(playerId, roomId, puzzleId, solution) for asylum puzzles"
        );
    }

    private boolean solveAnniesStoryPuzzle(String playerId) {
        Set<String> requiredClues = Set.of(
            "annies-letters",
            "burnt-drawing",
            "charred-wall",
            "scratcher-bear",
            "claw-marks"
        );

        boolean solved = gameStateService.hasDiscoveredAllClues(playerId, requiredClues);

        if (solved) {
            gameStateService.markPuzzleCompleted(playerId, "annies-story-puzzle");
        }

        return solved;
    }

    private boolean solveVentEscapePuzzle(String playerId) {
        boolean ventOpened = gameStateService.isVentOpened(playerId);

        if (ventOpened && !gameStateService.isPuzzleCompleted(playerId, "vent-escape-puzzle")) {
            gameStateService.markPuzzleCompleted(playerId, "vent-escape-puzzle");
            return true;
        }

        return ventOpened;
    }

    public int getClueProgress(String playerId) {
        return gameStateService.getClueCount(playerId);
    }

    public boolean isStoryPuzzleComplete(String playerId) {
        return gameStateService.isPuzzleCompleted(playerId, "annies-story-puzzle");
    }

    public boolean isVentEscapeComplete(String playerId) {
        return gameStateService.isPuzzleCompleted(playerId, "vent-escape-puzzle");
    }
}
