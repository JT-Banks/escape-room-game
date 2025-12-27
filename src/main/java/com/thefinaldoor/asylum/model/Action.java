package com.thefinaldoor.asylum.model;

import lombok.Data;

@Data
public class Action {
    private InteractionType actionType;
    private String result;
}
