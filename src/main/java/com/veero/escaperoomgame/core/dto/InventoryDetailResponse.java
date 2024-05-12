package com.veero.escaperoomgame.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class InventoryDetailResponse {

    private List<ItemDetail> items;

    public InventoryDetailResponse(List<ItemDetail> items) {
        this.items = items;
    }

    // Nested class or use an existing DTO to represent item details
    public static class ItemDetail {
        private String id;
        private String name;
        private String description;
    }

}
