package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.core.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@SuppressWarnings("unused")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{playerId}/")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable String playerId) {
        InventoryResponse inventory = inventoryService.getEntireInventory(playerId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/{playerId}/addItem/{itemId}")
    public ResponseEntity<InventoryResponse> addItem(@PathVariable String playerId, @PathVariable Item item) {
        try {
            boolean success = inventoryService.addItemToInventory(playerId, item);
            if (success) {
                return ResponseEntity.ok(new InventoryResponse(playerId, true, "Item added successfully."));
            } else {
                return ResponseEntity.badRequest().body(new InventoryResponse(playerId, false, "Failed to add item."));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new InventoryResponse(playerId, false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new InventoryResponse(playerId, false, "An unexpected error occurred."));
        }
    }

    @GetMapping("/getEntireInventory/{playerId}")
    public ResponseEntity<InventoryResponse> getEntireInventory(@PathVariable String playerId) {
        InventoryResponse inventory = inventoryService.getEntireInventory(playerId);
        return ResponseEntity.ok(inventory);
    }
}
