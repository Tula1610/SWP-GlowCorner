package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByOrderId(String orderId);
    List<Order> findByCustomerID(String customerID);
    List<Order> findByStatus(String status);
    List<Order> findByOrderDate(LocalDate orderDate);
}
