package com.veero.escaperoomgame.asylum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnnieRoom extends Room {

    private boolean isDark;
    private List<Clue> clues;
    private List<String> storyElements;
    private int timer;
    private int solvedPuzzlesCount;
    private int foundCluesCount;
    private String backgroundImage;
    private String ambientSound;

    public AnnieRoom(
            String id, String name, String description, boolean isLocked, List<Item> items, List<Puzzle> puzzles,
            List<String> hints, boolean isDark, List<Clue> clues, List<String> storyElements, int timer, int solvedPuzzlesCount,
            int foundCluesCount, String backgroundImage, String ambientSound)
    {
        super(id, name, description, isLocked, items, puzzles, hints);
        this.isDark = isDark;
        this.clues = clues;
        this.storyElements = storyElements;
        this.timer = timer;
        this.solvedPuzzlesCount = solvedPuzzlesCount;
        this.foundCluesCount = foundCluesCount;
        this.backgroundImage = backgroundImage;
        this.ambientSound = ambientSound;
    }

}
