package com.veero.escaperoomgame.asylum.dto;

import com.veero.escaperoomgame.asylum.model.InteractionType;
import lombok.Data;

@Data
public class InteractionRequest {
    private String objectId;
    private InteractionType interactionType;
    private String name;
    private String description;
}
