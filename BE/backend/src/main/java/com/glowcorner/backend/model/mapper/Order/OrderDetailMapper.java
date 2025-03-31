package com.glowcorner.backend.model.mapper.Order;

import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    // Convert OrderDetail entity to OrderDetailDTO
    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        return new OrderDetailDTO(
            orderDetail.getOrderID(),
            orderDetail.getProductID(),
            orderDetail.getQuantity(),
            orderDetail.getTotalAmount()
        );
    }

    // Convert OrderDetailDTO to OrderDetail entity
    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO, String orderID) {
        if (orderDetailDTO == null) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderID(orderID);
        orderDetail.setProductID(orderDetailDTO.getProductID());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setTotalAmount(orderDetailDTO.getTotalAmount());

        return orderDetail;
    }
}
