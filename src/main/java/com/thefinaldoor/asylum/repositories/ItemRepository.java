package com.thefinaldoor.asylum.repositories;

import com.thefinaldoor.asylum.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    Optional<Item> findByName(String name);
}
