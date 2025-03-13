package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(String orderID, OrderDetailDTO orderDetailDTO);
    OrderDetailDTO updateOrderDetail(String orderID, String productID, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetail(String orderID, String productID);
    List<OrderDetailDTO> getOrderDetailByOrderID(String orderID);
}
