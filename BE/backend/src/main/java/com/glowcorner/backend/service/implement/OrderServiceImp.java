package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.model.DTO.Order.OrderDTO;
import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;
import com.glowcorner.backend.model.DTO.Order.OrderInfoDTO;
import com.glowcorner.backend.model.DTO.request.Order.CreateOrderRequest;
import com.glowcorner.backend.model.DTO.request.Order.CustomerCreateOrderRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.Order.Manager.CreateOrderRequestMapper;
import com.glowcorner.backend.model.mapper.CreateMapper.Order.Customer.CustomerCreateOrderRequestMapper;
import com.glowcorner.backend.model.mapper.Order.OrderDetailMapper;
import com.glowcorner.backend.model.mapper.Order.OrderMapper;
import com.glowcorner.backend.repository.OrderDetailRepository;
import com.glowcorner.backend.repository.OrderRepository;
import com.glowcorner.backend.repository.ProductRepository;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.CounterService;
import com.glowcorner.backend.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderMapper orderMapper;

    @Autowired
    private CounterService counterService;

    private final OrderDetailMapper orderDetailMapper;
    private final CreateOrderRequestMapper createOrderRequestMapper;
    private final CustomerCreateOrderRequestMapper customerCreateOrderRequestMapper;

    public OrderServiceImp(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductRepository productRepository, UserRepository userRepository, OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, CreateOrderRequestMapper createOrderRequestMapper, CustomerCreateOrderRequestMapper customerCreateOrderRequestMapper) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.createOrderRequestMapper = createOrderRequestMapper;
        this.customerCreateOrderRequestMapper = customerCreateOrderRequestMapper;
    }

    /* Order
    * */

    // Create order
    @Override
    public OrderDTO createOrder(CreateOrderRequest request) {
        User user = userRepository.findByUserID(request.getCustomerID())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if (user.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("Customer ID required!");
        }

        Order order = createOrderRequestMapper.fromCreateRequest(request);
        order.setTotalAmount(calculateTotalAmount(order.getOrderDetails()));
        order = orderRepository.save(order);
        return orderMapper.toOrderDTO(order);
    }

    // Update order
    @Override
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) {
        // Find existing order
        Order existingOrder = orderRepository.findByOrderID(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update
        if (orderDTO.getCustomerID() != null) existingOrder.setCustomerID(orderDTO.getCustomerID());
        if (orderDTO.getOrderDate() != null) existingOrder.setOrderDate(orderDTO.getOrderDate());
        if (orderDTO.getStatus() != null) existingOrder.setStatus(orderDTO.getStatus());
        if (orderDTO.getTotalAmount() != null) existingOrder.setTotalAmount(calculateTotalAmount(existingOrder.getOrderDetails()));
        if (orderDTO.getOrderDetails() != null)
            existingOrder.setOrderDetails(orderDTO.getOrderDetails().stream()
                .map(orderDetailDTO -> orderDetailMapper.toOrderDetail(orderDetailDTO, orderId))
                .collect(Collectors.toList()));

        // Save
        Order savedOrder = orderRepository.save(existingOrder);

        // Convert to DTO
        return orderMapper.toOrderDTO(savedOrder);
    }

    // Delete order
    @Override
    public void deleteOrder(String orderId) {
        Order existingOrder = orderRepository.findByOrderID(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }

    // Customer create order
    @Override
    public OrderDTO customerCreateOrder(CustomerCreateOrderRequest request) {
        Order order = customerCreateOrderRequestMapper.fromCustomerCreateRequest(request);
        order.setTotalAmount(calculateTotalAmount(order.getOrderDetails()));
        order = orderRepository.save(order);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        orderDetailRepository.saveAll(orderDetails);
        return orderMapper.toOrderDTO(order);
    }



    // Get all orders
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get order by order ID
    @Override
    public OrderDTO getOrderById(String orderId) {
        if (orderRepository.findByOrderID(orderId).isPresent())
            return orderMapper.toOrderDTO(orderRepository.findByOrderID(orderId).get());
        return null;
    }

    // Get orders by customer ID
    @Override
    public List<OrderDTO> getOrdersByCustomerID(String customerID) {
        List<Order> orders = orderRepository.findByCustomerID(customerID);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get orders by status
    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get orders by order date
    @Override
    public List<OrderDTO> getOrdersByOrderDate(LocalDate orderDate) {
        List<Order> orders = orderRepository.findByOrderDate(orderDate);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // \\ For customer \\
    // Get orders by status and customerID
    public List<OrderDTO> getOrdersByStatusAndCustomerID(String status, String userID) {
        List<Order> orders = orderRepository.findByStatusAndCustomerID(status, userID);
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    // Get orders by order date and customerID
    public List<OrderDTO> getOrdersByOrderDateAndCustomerID(LocalDate orderDate, String userID) {
        List<Order> orders = orderRepository.findByOrderDateAndCustomerID(orderDate, userID);
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
        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailDTO, orderId);
        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailDTO(orderDetail);
    }

    // Update order detail
    @Override
    public OrderDetailDTO updateOrderDetail(String orderID, String productID, OrderDetailDTO orderDetailDTO) {
        try{
        OrderDetail existingOrderDetail = orderDetailRepository.findByOrderIDAndProductID(orderID, productID)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

            if (orderDetailDTO.getOrderID() != null) existingOrderDetail.setOrderID(orderDetailDTO.getOrderID());
            if (orderDetailDTO.getProductID() != null) existingOrderDetail.setProductID(orderDetailDTO.getProductID());
            if (orderDetailDTO.getQuantity() != null) existingOrderDetail.setQuantity(orderDetailDTO.getQuantity());
            if (orderDetailDTO.getPrice() != null) existingOrderDetail.setPrice(orderDetailDTO.getPrice());

            existingOrderDetail = orderDetailRepository.save(existingOrderDetail);
            return orderDetailMapper.toOrderDetailDTO(existingOrderDetail);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update order detail: " + e.getMessage(), e);
        }
    }

    // Get order info by order ID
    @Override
    public OrderInfoDTO getOrderInfoByOrderID(String userID, String orderID) {
        // 1. Find the Order by orderID and userID
        Order order = orderRepository.findByOrderIDAndCustomerID(orderID, userID)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 2. Find all OrderDetails by orderID
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderID(orderID);
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("Order details not found");
        }

        // 3. Fetch User info by userID
        User user = userRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 4. Fetch Product info for each OrderDetail
        List<OrderInfoDTO.OrderDetailItemDTO> orderDetailItems = orderDetails.stream().map(orderDetail -> {
            // Fetch Product by productID
            Product product = productRepository.findByProductID(orderDetail.getProductID())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderInfoDTO.OrderDetailItemDTO item = new OrderInfoDTO.OrderDetailItemDTO();
            item.setProductID(orderDetail.getProductID());
            item.setQuantity(orderDetail.getQuantity());
            item.setPrice(orderDetail.getPrice());
            item.setName(product.getProductName());
            item.setImage(product.getImage_url());
            return item;
        }).collect(Collectors.toList());

        // 5. Calculate total amount
        Long totalAmount = calculateTotalAmount(orderDetails);

        // 6. Build the response
        OrderInfoDTO response = new OrderInfoDTO();
        response.setOrderID(orderID);

        OrderInfoDTO.CustomerInfoDTO customerInfo = new OrderInfoDTO.CustomerInfoDTO();
        customerInfo.setName(user.getFullName());
        customerInfo.setEmail(user.getEmail());
        customerInfo.setPhone(user.getPhone());
        customerInfo.setAddress(user.getAddress());
        response.setCustomerInfo(customerInfo);

        response.setOrderDetails(orderDetailItems);
        response.setTotalAmount(totalAmount);

        return response;
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
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderID(orderID);

        return orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailDTO)
                .collect(Collectors.toList());
    }

    /* Calculate Total Amount */
    private Long calculateTotalAmount(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToLong(detail -> detail.getQuantity() * detail.getPrice())
                .sum();
    }
}
