package com.glowcorner.backend.model.DTO.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CartItemDTO {

    String userID;
    String productID;
    int quantity;

    Long totalAmount;
    Long discountedTotalAmount;

}
