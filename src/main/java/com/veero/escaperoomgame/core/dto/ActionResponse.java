package com.veero.escaperoomgame.core.dto;

import lombok.Data;

@Data
public class ActionResponse {

    private boolean success;

    private String message;

    public ActionResponse(boolean bool, String result) {
    }
}
