package com.glowcorner.backend.model.mapper.Order;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.model.DTO.Order.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

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
                .map(orderDetailMapper::toOrderDetailDTO)
                .collect(Collectors.toList())
        );
    }

}
