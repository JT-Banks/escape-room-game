package com.veero.escaperoomgame.asylum.dto;

import com.veero.escaperoomgame.asylum.model.Item;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InventoryRequest {

    private String playerId;

    private String itemId;

    private Item item;

    private String itemName;

}
