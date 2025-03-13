package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.model.DTO.OrderDTO;
import com.glowcorner.backend.model.mapper.OrderDetailMapper;
import com.glowcorner.backend.model.mapper.OrderMapper;
import com.glowcorner.backend.repository.OrderRepository;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final OrderMapper orderMapper;

    private final OrderDetailMapper orderDetailMapper;

    public OrderServiceImp(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    // Create order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        User user = userRepository.findByUserId(orderDTO.getCustomerID())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if (user.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("Only customers can create orders");
        }

        Order order = orderMapper.toOrder(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toOrderDTO(order);
    }

    // Update order
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) {
        // Find existing order
        Order existingOrder = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update
        existingOrder.setCustomerID(orderDTO.getCustomerID());
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setTotalAmount(orderDTO.getTotalAmount());
        existingOrder.setOrderDetails(orderDTO.getOrderDetails().stream()
                .map(orderDetailMapper::toOrderDetail)
                .collect(Collectors.toList()));

        // Save
        Order savedOrder = orderRepository.save(existingOrder);

        // Convert to DTO
        return orderMapper.toOrderDTO(savedOrder);
    }

    // Delete order
    public void deleteOrder(String orderId) {
        Order existingOrder = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }

    // Get all orders
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get order by order ID
    public OrderDTO getOrderById(String orderId) {
        if (orderRepository.findByOrderId(orderId).isPresent())
            return orderMapper.toOrderDTO(orderRepository.findByOrderId(orderId).get());
        return null;
    }

    // Get orders by customer ID
    public List<OrderDTO> getOrdersByCustomerID(String customerID) {
        List<Order> orders = orderRepository.findByCustomerID(customerID);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get orders by status
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get orders by order date
    public List<OrderDTO> getOrdersByOrderDate(LocalDate orderDate) {
        List<Order> orders = orderRepository.findByOrderDate(orderDate);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }
}
