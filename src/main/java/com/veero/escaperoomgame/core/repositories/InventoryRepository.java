package com.veero.escaperoomgame.core.repositories;

import com.veero.escaperoomgame.core.dto.Inventory;
import com.veero.escaperoomgame.core.model.AbstractInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

public interface InventoryRepository extends MongoRepository<AbstractInventory, String> {

    @NonNull
    AbstractInventory save(@NonNull AbstractInventory inventory);

}
