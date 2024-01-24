package com.veero.escaperoomgame.asylum.controllers;

import com.veero.escaperoomgame.asylum.dto.InventoryRequest;
import com.veero.escaperoomgame.core.dto.InventoryResponse;
import com.veero.escaperoomgame.asylum.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class ItemManagementController {

    @Autowired
    private final InventoryService inventoryService;

    @Autowired
    public ItemManagementController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<InventoryResponse> addItemToInventory(@RequestBody InventoryRequest request) {
        boolean success = inventoryService.addItem(request.getPlayerId(), request.getItemId());
        return ResponseEntity.ok(new InventoryResponse(success));
    }
}
