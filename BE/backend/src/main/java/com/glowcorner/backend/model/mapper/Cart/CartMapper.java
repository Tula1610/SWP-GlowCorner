package com.glowcorner.backend.model.mapper.Cart;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.model.DTO.Cart.CartDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        return new CartDTO(
                cart.getUserID(),
                cart.getItems().stream()
                        .map(cartItemMapper::toCartItemDTO)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    public Cart toCart(CartDTO cartDTO) {
        if (cartDTO == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setUserID(cartDTO.getUserID());
        cart.setItems(cartDTO.getItems().stream()
                .map(cartItemMapper::toCartItem)
                .collect(Collectors.toList())
        );

        return cart;
    }


}
