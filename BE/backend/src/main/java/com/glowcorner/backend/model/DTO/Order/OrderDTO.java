package com.glowcorner.backend.model.DTO.Order;

import com.glowcorner.backend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderDTO {

    String orderID;
    String customerID;
    LocalDate orderDate;
    OrderStatus status;
    Long totalAmount;
    List<OrderDetailDTO> orderDetails;

}
