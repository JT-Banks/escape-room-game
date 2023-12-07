package com.veero.escaperoomgame.asylum.model;

import lombok.Getter;

@Getter
public enum InteractionType {

    WALL("wall"),
    ITEM("item"),
    PUZZLE("puzzle"),
    DOOR("door"),
    HINT("hint"),
    DESK("desk"),
    FUSE("fuse"),
    VENT("vent"),
    INSPECT("inspect");

    private final String type;

    InteractionType(String type) {
        this.type = type;
    }

}
