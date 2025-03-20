package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findCartItemsByUserID(String userID);
    void deleteCartItemByUserID(String userID);
}
