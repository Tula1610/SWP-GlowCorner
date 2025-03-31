package com.glowcorner.backend.model.mapper.Cart;

import com.glowcorner.backend.entity.mongoDB.CartItem;
import com.glowcorner.backend.model.DTO.Cart.CartItemDTO;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toCartItemDTO(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        return new CartItemDTO(
                cartItem.getUserID(),
                cartItem.getProductID(),
                cartItem.getQuantity(),
                cartItem.getTotalAmount(),
                cartItem.getDiscountedTotalAmount()
        );
    }

    public CartItem toCartItem(CartItemDTO cartItemDTO) {
        if (cartItemDTO == null) {
            return null;
        }

        CartItem cartItem = new CartItem();
        cartItem.setUserID(cartItemDTO.getUserID());
        cartItem.setProductID(cartItemDTO.getProductID());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setTotalAmount(cartItemDTO.getTotalAmount());
        cartItem.setDiscountedTotalAmount(cartItemDTO.getDiscountedTotalAmount());

        return cartItem;
    }

}
