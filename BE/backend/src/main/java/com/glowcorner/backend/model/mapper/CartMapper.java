package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.model.DTO.CartDTO;

public class CartMapper {

    public CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        return new CartDTO(
                cart.getCartID(),
                cart.getItems(),
                cart.getQuantity()
        );
    }

    public Cart toCart(CartDTO cartDTO) {
        if (cartDTO == null) {
            return null;
        }

        return new Cart(
                cartDTO.getCartID(),
                cartDTO.getItems(),
                cartDTO.getQuantity()
        );
    }


}
