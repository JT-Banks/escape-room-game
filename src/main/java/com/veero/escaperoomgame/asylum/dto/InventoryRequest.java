package com.veero.escaperoomgame.asylum.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InventoryRequest {

    private String playerId;

    private String itemId;

    private String itemName;

}
