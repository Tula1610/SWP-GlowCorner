package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.CartDTO;

public interface CartService {
    CartDTO getCartByUserID(String userID);

    void addItemToCart(String userID, Product newProduct, int quantity);
    void removeItemFromCart(String userID, String productID);
    void clearCart(String userID);
}
