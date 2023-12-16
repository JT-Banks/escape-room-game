package com.veero.escaperoomgame.asylum.dto;

public class PuzzleResponse {
    public void checkIfSolved(boolean isSolved) {
        /*
        Logic to check if a puzzle is solved:

        Currently, in the database, an object within a room has [clues] and [relatedObjects].
        So, for example... a puzzle in Annie's room lets say the bed, it has 2 clues: 1.) the bed is in rough shape,
        2.) The impression on the bed.
        The 2nd clue denotes that something was set there repeatedly and for an extended amount of time.
        So, this clue leads to a relatedObject -> 'teddyBear' which isn't in the room.

        I guess the main goal of this first puzzle is to lead to another puzzle, which is the teddyBear puzzle.
        This puzzle doesn't exactly solve anything, but it does reveal story elements that can help illuminate some
        information and clues about the story and room.
         */
    }
}
