package com.glowcorner.backend.model.DTO.request.Order;

import com.glowcorner.backend.model.DTO.Order.OrderDetailDTO;
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
public class CreateOrderRequest {

    String customerID;
    LocalDate orderDate;
    String status;
    Integer totalAmount;
    List<OrderDetailDTO> orderDetails;

}
