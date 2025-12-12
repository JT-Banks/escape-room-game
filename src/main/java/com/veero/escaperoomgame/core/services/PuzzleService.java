package com.veero.escaperoomgame.core.services;

public interface PuzzleService {
    boolean solvePuzzle(String roomId, String puzzleId, String solution);
}
