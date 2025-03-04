package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.CartItem;
import org.bson.types.ObjectId;

import java.util.List;

public interface CartService {
    List<Cart> getCartByUserID(ObjectId userID);
    Cart getCartByCartID(ObjectId cartID);
    void addItemToCart(ObjectId cartID, CartItem newItem);
    void removeItemFromCart(ObjectId cartID, ObjectId productID);
    void clearCart(ObjectId cartID);
}
