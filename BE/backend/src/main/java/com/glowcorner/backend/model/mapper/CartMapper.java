package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.Cart;
import com.glowcorner.backend.model.DTO.CartDTO;

public class CartMapper {

    public CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        return CartDTO.builder()
                .cartID(cart.getCartID().toHexString()) // Convert ObjectId to String
                .userID(cart.getUserID().toHexString()) // Convert ObjectId to String
                .build();
    }


}
