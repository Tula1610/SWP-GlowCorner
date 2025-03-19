package com.glowcorner.backend.model.mapper.CreateMapper.Order.Customer;

import com.glowcorner.backend.entity.mongoDB.OrderDetail;
import com.glowcorner.backend.model.DTO.request.Order.CustomerOrderDetailRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderDetailMapper {

    // Convert OrderDetailDTO to OrderDetail entity
    public OrderDetail toOrderDetail(CustomerOrderDetailRequest request) {
        if (request == null) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductID(request.getProductID());
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setPrice(request.getPrice());

        return orderDetail;
    }

}
