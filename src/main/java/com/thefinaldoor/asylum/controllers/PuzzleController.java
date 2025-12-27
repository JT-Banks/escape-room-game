package com.thefinaldoor.asylum.controllers;

import com.thefinaldoor.asylum.services.AsylumPuzzleServiceImpl;
import com.thefinaldoor.generated.model.PuzzleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms/{roomId}/puzzles")
public class PuzzleController {

    private final AsylumPuzzleServiceImpl puzzleService;

    public PuzzleController(AsylumPuzzleServiceImpl puzzleService) {
        this.puzzleService = puzzleService;
    }

    @PostMapping("/{puzzleId}/solve")
    public ResponseEntity<PuzzleResponse> solve(
            @PathVariable String roomId,
            @PathVariable String puzzleId,
            @RequestParam String solution) {
        boolean isSolved = puzzleService.solvePuzzle(roomId, puzzleId, solution);

        PuzzleResponse response = new PuzzleResponse();
        response.setSolved(isSolved);
        response.setSuccess(isSolved);
        response.setMessage(isSolved ? "Puzzle solved successfully!" : "Incorrect solution. Try again.");

        return ResponseEntity.ok(response);
    }
}
