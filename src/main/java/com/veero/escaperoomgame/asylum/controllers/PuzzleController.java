package com.veero.escaperoomgame.asylum.controllers;

import com.veero.escaperoomgame.asylum.dto.PuzzleResponse;
import com.veero.escaperoomgame.asylum.service.AsylumPuzzleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PuzzleController {

    @Autowired
    private AsylumPuzzleServiceImpl asylumPuzzleServiceImpl;

    @Autowired
    private PuzzleResponse response;

    @GetMapping("/solve/{puzzleId}")
    public ResponseEntity<?> solve(@PathVariable String puzzleId,
                                   @RequestParam String roomId,
                                   @RequestParam String solution) {
        boolean isSolved = asylumPuzzleServiceImpl.solvePuzzle(roomId, puzzleId, solution);
        response.checkIfSolved(isSolved);
        return ResponseEntity.ok(response);
    }
}
