package com.glowcorner.backend.model.mapper.CreateMapper;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.model.DTO.request.Order.CreateOrderRequest;
import com.glowcorner.backend.model.DTO.request.Order.CustomerCreateOrderRequest;
import com.glowcorner.backend.model.mapper.Order.OrderDetailMapper;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerCreateOrderRequestMapper {
    private final OrderDetailMapper orderDetailMapper;

    private final CounterServiceImpl counterService;

    public CustomerCreateOrderRequestMapper(OrderDetailMapper orderDetailMapper, CounterServiceImpl counterService) {
        this.orderDetailMapper = orderDetailMapper;
        this.counterService = counterService;
    }

    public Order fromCustomerCreateRequest (CustomerCreateOrderRequest request){
        if (request == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderID(counterService.getNextProductID());
        order.setOrderDate(request.getOrderDate());
        order.setStatus(request.getStatus());
        order.setTotalAmount(request.getTotalAmount());
        order.setOrderDetails(request.getOrderDetails().stream()
                .map(orderDetailMapper::toOrderDetail)
                .collect(Collectors.toList())
        );

        return order;
    }
}
