package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.repository.CartRepository;
import com.glowcorner.backend.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private CartRepository cartRepository;

    public CartServiceImp(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Get Cart by UserID
    @Override
    public List<Cart> getCartByUserID(ObjectId userID) {
        return cartRepository.findByUserId(userID)
                .orElse(new ArrayList<>());
    }

    // Get Cart by CartID
    @Override
    public Cart getCartByCartID(ObjectId cartID) {
        return cartRepository.findById(cartID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Add item to Cart
    @Override
    public void addItemToCart(ObjectId cartID, CartItem newItem) {
        Cart cart = cartRepository.findById(cartID).orElseThrow(() -> new RuntimeException("Cart not found"));

        // Check if the item  already exists in cart
        boolean itemExists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductID().equals(newItem.getProductID())) {
                // Update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + newItem.getQuantity());
                itemExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the cart
        if (!itemExists) {
            cart.getItems().add(newItem);
        }

        // Save the updated cart
        cartRepository.save(cart);
    }

    // Remove item from Cart
    @Override
    public void removeItemFromCart(ObjectId cartID, ObjectId productID) {
        Cart cart = cartRepository.findById(cartID).orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(i -> i.getProductID().equals(productID));

        cartRepository.save(cart);
    }

    // Clear entire Cart
    @Override
    public void clearCart(ObjectId cartID) {
        Cart cart = cartRepository.findById(cartID).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

}
