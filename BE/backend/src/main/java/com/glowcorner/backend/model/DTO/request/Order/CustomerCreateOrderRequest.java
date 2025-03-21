package com.glowcorner.backend.model.DTO.request.Order;


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
public class CustomerCreateOrderRequest {

    String customerID;
    LocalDate orderDate;
    String status;
    Long totalAmount;
    private List<CustomerOrderDetailRequest> orderDetails;

}
