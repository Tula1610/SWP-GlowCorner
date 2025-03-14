package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.Order.OrderDTO;
import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    /* Order CRUD */
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(String orderId, OrderDTO orderDTO);
    void deleteOrder(String orderId);

    /* Order Query */
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(String orderId);
    List<OrderDTO> getOrdersByCustomerID(String customerID);
    List<OrderDTO> getOrdersByStatus(String status);
    List<OrderDTO> getOrdersByOrderDate(LocalDate orderDate);

    /* OrderDetail CRUD */
    OrderDetailDTO createOrderDetail(String orderID, OrderDetailDTO orderDetailDTO);
    OrderDetailDTO updateOrderDetail(String orderID, String productID, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetail(String orderID, String productID);

    /* OrderDetail Query */
    List<OrderDetailDTO> getOrderDetailByOrderID(String orderID);

}
