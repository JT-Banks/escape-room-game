package com.veero.escaperoomgame.asylum.model;

public enum InteractionType {

    WALL("wall"),
    ITEM("item"),
    PUZZLE("puzzle"),
    DOOR("door"),
    HINT("hint");

    private final String type;

    InteractionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
