package com.thefinaldoor.asylum.controllers;

import com.thefinaldoor.asylum.services.AsylumPuzzleServiceImpl;
import com.thefinaldoor.generated.model.PuzzleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/players/{playerId}/rooms/{roomId}/puzzles")
public class PuzzleController {

    private final AsylumPuzzleServiceImpl puzzleService;

    public PuzzleController(AsylumPuzzleServiceImpl puzzleService) {
        this.puzzleService = puzzleService;
    }

    @PostMapping("/{puzzleId}/solve")
    public ResponseEntity<PuzzleResponse> solvePuzzle(
            @PathVariable String playerId,
            @PathVariable String roomId,
            @PathVariable String puzzleId,
            @RequestParam(required = false) String solution) {

        boolean isSolved = puzzleService.solvePuzzle(playerId, roomId, puzzleId, solution);

        PuzzleResponse response = new PuzzleResponse();
        response.setSolved(isSolved);
        response.setSuccess(isSolved);

        if (puzzleId.equals("annies-story-puzzle")) {
            int clueCount = puzzleService.getClueProgress(playerId);
            response.setMessage(isSolved
                ? "You've pieced together Annie's tragic story. The vent under the desk is your way out!"
                : String.format("You've found %d/5 clues. Keep exploring to discover the full story.", clueCount));
        } else if (puzzleId.equals("vent-escape-puzzle")) {
            response.setMessage(isSolved
                ? "You've successfully opened the vent and found the cell key! You can now escape."
                : "You need a screwdriver to open the vent. Perhaps you can find one in another room?");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{puzzleId}/progress")
    public ResponseEntity<Map<String, Object>> getPuzzleProgress(
            @PathVariable String playerId,
            @PathVariable String puzzleId) {

        Map<String, Object> progress = new HashMap<>();

        if (puzzleId.equals("annies-story-puzzle")) {
            progress.put("cluesFound", puzzleService.getClueProgress(playerId));
            progress.put("cluesRequired", 5);
            progress.put("completed", puzzleService.isStoryPuzzleComplete(playerId));
        } else if (puzzleId.equals("vent-escape-puzzle")) {
            progress.put("completed", puzzleService.isVentEscapeComplete(playerId));
        }

        return ResponseEntity.ok(progress);
    }
}
