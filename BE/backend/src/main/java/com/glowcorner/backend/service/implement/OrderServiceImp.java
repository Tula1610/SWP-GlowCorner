package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.repository.OrderRepository;
import com.glowcorner.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Order order) {
        User user = userRepository.findByUserId(order.getCustomerID())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if (user.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("Only customers can create orders");
        }

        return orderRepository.save(order);
    }

}
