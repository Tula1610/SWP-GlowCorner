package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.model.DTO.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    // Convert Order entity to OrderDTO
    public OrderDTO toOrderDTO(Order order) {
        if (order == null) {
            return null;
        }
        return new OrderDTO(
            order.getOrderID(),
            order.getCustomerID(),
            order.getOrderDate(),
            order.getStatus(),
            order.getTotalAmount(),
            order.getOrderDetails().stream()
                .map(OrderDetailMapper::toOrderDetailDTO)
                .collect(Collectors.toList())
        );
    }

    // Convert OrderDTO to Order entity
    public Order toOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderID(orderDTO.getOrderID());
        order.setCustomerID(orderDTO.getCustomerID());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderDetails(orderDTO.getOrderDetails().stream()
            .map(OrderDetailMapper::toOrderDetail)
            .collect(Collectors.toList())
        );

        return order;
    }

}
