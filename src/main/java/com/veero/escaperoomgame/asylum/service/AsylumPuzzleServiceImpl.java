package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.core.service.PuzzleService;
import org.springframework.stereotype.Service;

@Service
public class AsylumPuzzleServiceImpl implements PuzzleService {

    public boolean solvePuzzle(String roomId, String puzzleId, String solution) {
        //Determine the type of puzzle based on puzzleId or roomId
        //Delegate to specific puzzle-solving logic
        return switch (puzzleId) {
            case "anniePuzzle" -> solveAnniePuzzle1(solution);
            case "janitorPuzzle" -> solveAnniePuzzle2(solution);
            default -> throw new IllegalArgumentException("Unknown puzzleId: " + puzzleId);
        };
    }

        private boolean solveAnniePuzzle1(String solution) {
            return solution.equals("annie");
        }

        private boolean solveAnniePuzzle2(String solution) {
            return solution.equals("janitor");
    }
}
