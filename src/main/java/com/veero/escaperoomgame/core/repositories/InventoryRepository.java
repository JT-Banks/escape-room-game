package com.veero.escaperoomgame.core.repositories;

import com.veero.escaperoomgame.core.dto.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    @NonNull
    Inventory save(@NonNull Inventory inventory);
}
