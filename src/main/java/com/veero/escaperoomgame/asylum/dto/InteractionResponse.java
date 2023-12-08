package com.veero.escaperoomgame.asylum.dto;

import com.veero.escaperoomgame.asylum.model.Action;
import lombok.Data;

import java.util.List;

@Data
public class InteractionResponse {
    private String name;
    private String description;
    private List<String> clues;
    private List<Action> actions;
    private List<String> relatedObjects;
}
