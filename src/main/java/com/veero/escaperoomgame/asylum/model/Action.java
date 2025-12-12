package com.veero.escaperoomgame.asylum.model;

import lombok.Data;

@Data
public class Action {
    private InteractionType actionType;
    private String result;
}
