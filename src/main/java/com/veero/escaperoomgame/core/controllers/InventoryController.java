package com.veero.escaperoomgame.core.controllers;

import com.veero.escaperoomgame.asylum.model.Item;
import com.veero.escaperoomgame.generated.model.InventoryResponse;
import com.veero.escaperoomgame.core.services.InventoryService;
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
        try {
            boolean success = inventoryService.addItemToInventory(playerId, item);
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(success);
            response.setMessage(success ? "Item added successfully." : "Failed to add item.");
            if (success) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (IllegalArgumentException e) {
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(false);
            response.setMessage("An unexpected error occurred.");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<InventoryResponse> removeItem(
            @PathVariable String playerId,
            @PathVariable String itemId) {
        try {
            boolean success = inventoryService.removeItemFromInventory(playerId, itemId);
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(success);
            response.setMessage(success ? "Item used/removed successfully." : "Item not found in inventory.");
            return success ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            InventoryResponse response = new InventoryResponse();
            response.setPlayerId(playerId);
            response.setSuccess(false);
            response.setMessage("An unexpected error occurred.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
