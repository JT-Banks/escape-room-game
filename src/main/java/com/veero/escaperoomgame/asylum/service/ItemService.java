package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

public class ItemService {

    @Autowired
    private Player player;

    //Handle item logic here, such as descriptions, interactions, etc.
    //TODO: Add all the logic for items
    public void useItem(String itemId) {
        Item item = findItemById(itemId);
        if (!ObjectUtils.isEmpty(item)) {
            //Do something with the item
        } else {
            throw new IllegalArgumentException("Player does not have item with ID: " + itemId);
        }
    }

    public void examineItem(String itemId) {
        Item item = findItemById(itemId);
        if (!ObjectUtils.isEmpty(item)) {
            //Print the item's description
        } else {
            throw new IllegalArgumentException("Player does not have item with ID: " + itemId);
        }
    }

    public String getItemDescription(String itemId) {
        Item item = findItemById(itemId);
        if (!ObjectUtils.isEmpty(item)) {
            return item.getDescription();
        } else {
            throw new IllegalArgumentException("Player does not have item with ID: " + itemId);
        }
    }

    private Item findItemById(String itemId) {
        Optional<Item> itemOptional = player.getInventory().getItem(itemId);
        return itemOptional.orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }
}
