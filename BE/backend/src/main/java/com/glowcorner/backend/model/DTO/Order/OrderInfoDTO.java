package com.glowcorner.backend.model.DTO.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderInfoDTO {

    String orderID;

    CustomerInfoDTO customerInfo;

    List<OrderDetailItemDTO> orderDetails;

    Long totalAmount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public static class CustomerInfoDTO {
        String name;
        String email;
        String phone;
        String address;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public static class OrderDetailItemDTO {
        String productID;
        String name;
        Long price;
        Integer quantity;
        String image;
    }
}