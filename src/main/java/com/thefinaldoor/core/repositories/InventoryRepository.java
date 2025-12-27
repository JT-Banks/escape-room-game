package com.thefinaldoor.core.repositories;

import com.thefinaldoor.core.models.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    @NonNull
    Inventory save(@NonNull Inventory inventory);
}
