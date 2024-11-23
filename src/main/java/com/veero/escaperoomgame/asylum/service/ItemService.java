package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemService {

    @Autowired
    private Player player;

    //Handle item logic here, such as descriptions, interactions, etc.
    public void useItem(String itemId) {
        if (player.getInventory().hasItem(itemId)) {
            //Do something with the item
        } else {
            throw new IllegalArgumentException("Player does not have item with ID: " + itemId);
        }
    }

    public void examineItem(String itemId) {
        if (player.getInventory().hasItem(itemId)) {
            //Print the item's description
        } else {
            throw new IllegalArgumentException("Player does not have item with ID: " + itemId);
        }
    }
}
