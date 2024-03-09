package com.veero.escaperoomgame.asylum.service;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.asylum.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StarterItemService {

    private final ItemRepository itemRepository;

    public StarterItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getStarterItem(String itemName) {
        // Lookup the item by name from a repository or predefined list
        Optional<Item> item = itemRepository.findByName(itemName);
        return item.orElseGet(this::getDefaultStarterItem);
    }

    private Item getDefaultStarterItem() {
        // Return a default item if the requested item is not found
        return new Item("1", "1", "Flashlight", "Default description", "Default type", "Default use");
    }
}
