package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {
}
