package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
}
