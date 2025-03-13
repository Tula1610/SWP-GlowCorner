package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import com.glowcorner.backend.model.DTO.OrderDetailDTO;
import com.glowcorner.backend.model.mapper.OrderDetailMapper;
import com.glowcorner.backend.repository.OrderDetailRepository;
import com.glowcorner.backend.service.interfaces.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailServiceImp(OrderDetailRepository orderDetailRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

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
        OrderDetail existingOrderDetail = orderDetailRepository.findByOrderIdAndProductID(orderID, productID)
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
        OrderDetail existingOrderDetail = orderDetailRepository.findByOrderIdAndProductID(orderID, productID)
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
