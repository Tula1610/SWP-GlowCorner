package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {
    Optional<OrderDetail> findByOrderId(String orderId);
    Optional<OrderDetail> findByOrderIdAndProductID(String orderId, String productID);

    List<OrderDetail> findByOrderID(String orderID);
}
