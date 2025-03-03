package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {
}
