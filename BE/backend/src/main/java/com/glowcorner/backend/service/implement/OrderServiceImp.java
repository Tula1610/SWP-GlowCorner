package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.model.DTO.Order.OrderDTO;
import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;
import com.glowcorner.backend.model.mapper.Order.OrderDetailMapper;
import com.glowcorner.backend.model.mapper.Order.OrderMapper;
import com.glowcorner.backend.repository.OrderDetailRepository;
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

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    private final OrderMapper orderMapper;

    private final OrderDetailMapper orderDetailMapper;

    public OrderServiceImp(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserRepository userRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    /* Order
    * */

    // Create order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        User user = userRepository.findByUserID(orderDTO.getCustomerID())
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
        Order existingOrder = orderRepository.findByOrderID(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update
        if (orderDTO.getCustomerID() != null) existingOrder.setCustomerID(orderDTO.getCustomerID());
        if (orderDTO.getOrderDate() != null) existingOrder.setOrderDate(orderDTO.getOrderDate());
        if (orderDTO.getStatus() != null) existingOrder.setStatus(orderDTO.getStatus());
        if (orderDTO.getTotalAmount() != null) existingOrder.setTotalAmount(orderDTO.getTotalAmount());
        if (orderDTO.getOrderDetails() != null)

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
        Order existingOrder = orderRepository.findByOrderID(orderId)
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
        if (orderRepository.findByOrderID(orderId).isPresent())
            return orderMapper.toOrderDTO(orderRepository.findByOrderID(orderId).get());
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

    /* OrderDetail
    * */

    // Create order detail
    @Override
    public OrderDetailDTO createOrderDetail(String orderId, OrderDetailDTO orderDetailDTO) {
        orderDetailDTO.setOrderID(orderId);
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailDTO);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailDTO(orderDetail);
    }

    // Update order detail
    @Override
    public OrderDetailDTO updateOrderDetail(String orderID, String productID, OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailRepository.findByOrderIDAndProductID(orderID, productID)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        existingOrderDetail.setOrderID(orderDetailDTO.getOrderID());
        existingOrderDetail.setProductID(orderDetailDTO.getProductID());
        existingOrderDetail.setQuantity(orderDetailDTO.getQuantity());
        existingOrderDetail.setPrice(orderDetailDTO.getPrice());

        existingOrderDetail = orderDetailRepository.save(existingOrderDetail);
        return orderDetailMapper.toOrderDetailDTO(existingOrderDetail);
    }

    // Delete order detail
    @Override
    public void deleteOrderDetail(String orderID, String productID) {
        OrderDetail existingOrderDetail = orderDetailRepository.findByOrderIDAndProductID(orderID, productID)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        orderDetailRepository.delete(existingOrderDetail);
    }

    // Get order details by order ID
    @Override
    public List<OrderDetailDTO> getOrderDetailByOrderID(String orderID) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderID(orderID);

        return orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailDTO)
                .collect(Collectors.toList());
    }
}
