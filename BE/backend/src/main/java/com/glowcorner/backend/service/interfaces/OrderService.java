package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.model.DTO.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(String orderId, OrderDTO orderDTO);
    void deleteOrder(String orderId);

    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(String orderId);
    List<OrderDTO> getOrdersByCustomerID(String customerID);
    List<OrderDTO> getOrdersByStatus(String status);
    List<OrderDTO> getOrdersByOrderDate(LocalDate orderDate);

}
