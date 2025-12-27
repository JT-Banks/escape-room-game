package com.thefinaldoor.asylum.model;

import lombok.Getter;

@Getter
public enum InteractionType {
    INSPECT("inspect"),
    INTERACT("interact"),
    PICK_UP("pick up"),
    OPEN("open");
    private final String type;
    InteractionType(String type) {
        this.type = type;
    }
}
