package com.glowcorner.backend.model.DTO.request.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CustomerOrderDetailRequest {

    String productID;
    Integer quantity;
    Integer price;

}
