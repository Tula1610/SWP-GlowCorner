package com.glowcorner.backend.controller.OrderController;

import com.glowcorner.backend.model.DTO.OrderDetailDTO;
import com.glowcorner.backend.service.interfaces.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/{orderId}/details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Create order detail
    @PostMapping
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@PathVariable String orderId, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO createdOrderDetail = orderDetailService.createOrderDetail(orderId, orderDetailDTO);
        return ResponseEntity.ok(createdOrderDetail);
    }

    // Update order detail
    @PutMapping("/{productID}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable String orderId, @PathVariable String productID, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO updatedOrderDetail = orderDetailService.updateOrderDetail(orderId, productID, orderDetailDTO);
        return ResponseEntity.ok(updatedOrderDetail);
    }

    // Delete order detail
    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable String orderId, @PathVariable String productID) {
        orderDetailService.deleteOrderDetail(orderId, productID);
        return ResponseEntity.noContent().build();
    }

    // Get all order details
    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails(@PathVariable String orderId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailByOrderID(orderId);
        return ResponseEntity.ok(orderDetails);
    }


}
