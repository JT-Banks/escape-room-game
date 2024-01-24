package com.veero.escaperoomgame.core.dto;

import lombok.Data;

@Data
public class ActionResponse {

    private boolean success;

    private String result;

    public ActionResponse(boolean success, String result) {
        this.success = success;
        this.result = result;
    }
}
