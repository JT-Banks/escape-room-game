package com.thefinaldoor.core.controllers;

import com.thefinaldoor.core.exceptions.ItemNotFoundException;
import com.thefinaldoor.generated.model.Item;
import com.thefinaldoor.generated.model.InventoryResponse;
import com.thefinaldoor.core.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players/{playerId}/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable String playerId) {
        InventoryResponse inventory = inventoryService.getEntireInventory(playerId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/items")
    public ResponseEntity<InventoryResponse> addItem(@PathVariable String playerId, @RequestBody Item item) {
        boolean success = inventoryService.addItemToInventory(playerId, item);
        InventoryResponse response = new InventoryResponse();
        response.setPlayerId(playerId);
        response.setSuccess(success);
        response.setMessage(success ? "Item added successfully." : "Failed to add item.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<InventoryResponse> removeItem(@PathVariable String playerId, @PathVariable String itemId) {
        String itemName = inventoryService.removeItemFromInventory(playerId, itemId);

        if (itemName == null) {
            throw new ItemNotFoundException(itemId);
        }

        InventoryResponse response = new InventoryResponse();
        response.setPlayerId(playerId);
        response.setSuccess(true);
        response.setMessage(String.format("Item '%s' used.", itemName));
        return ResponseEntity.ok(response);
    }
}
