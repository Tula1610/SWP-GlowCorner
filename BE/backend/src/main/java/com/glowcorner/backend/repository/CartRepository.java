package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {
    Optional<List<Cart>> findByUserId(ObjectId userID);
    Optional<Cart> findById(ObjectId cartID);
}
