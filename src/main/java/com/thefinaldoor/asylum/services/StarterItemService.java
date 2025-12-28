package com.thefinaldoor.asylum.services;

import com.thefinaldoor.asylum.model.Item;
import com.thefinaldoor.asylum.repositories.ItemRepository;
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
        return new Item("1", "1", "Flashlight",
                "Tiny flashlight, sometimes flickers but a good smack will get it working again", "Default type", "Default use");
    }
}
