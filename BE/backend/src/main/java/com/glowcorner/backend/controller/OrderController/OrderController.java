package com.glowcorner.backend.controller.OrderController;

import com.glowcorner.backend.model.DTO.Order.OrderDTO;
import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;
import com.glowcorner.backend.service.interfaces.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /* Order CRUD
    * */

    // Create order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    // Update order
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    /* Order Query
    * */

    // Get order by id
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId) {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get orders by customer id
    @GetMapping("/{customerID}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerID(@PathVariable String customerID) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerID(customerID);
        return ResponseEntity.ok(orders);
    }

    // Get orders by status
    @GetMapping("/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable String status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    // Get orders by order date
    @GetMapping("/{orderDate}")
    public ResponseEntity<List<OrderDTO>> getOrdersByOrderDate(@PathVariable LocalDate orderDate) {
        List<OrderDTO> orders = orderService.getOrdersByOrderDate(orderDate);
        return ResponseEntity.ok(orders);
    }



    /* OrderDetail CRUD
    * */

    // Create order detail
    @PostMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@PathVariable String orderId, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO createdOrderDetail = orderService.createOrderDetail(orderId, orderDetailDTO);
        return ResponseEntity.ok(createdOrderDetail);
    }

    // Update order detail
    @PutMapping("/{orderId}/details/{productID}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable String orderId, @PathVariable String productID, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO updatedOrderDetail = orderService.updateOrderDetail(orderId, productID, orderDetailDTO);
        return ResponseEntity.ok(updatedOrderDetail);
    }

    // Delete order detail
    @DeleteMapping("/{orderId}/details/{productID}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable String orderId, @PathVariable String productID) {
        orderService.deleteOrderDetail(orderId, productID);
        return ResponseEntity.noContent().build();
    }

    /* OrderDetail Query
    * */

    // Get all order details
    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails(@PathVariable String orderId) {
        List<OrderDetailDTO> orderDetails = orderService.getOrderDetailByOrderID(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}