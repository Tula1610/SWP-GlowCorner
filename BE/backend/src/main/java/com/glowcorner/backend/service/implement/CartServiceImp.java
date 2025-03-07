package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.mongoDB.CartItem;
import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.CartDTO;
import com.glowcorner.backend.model.mapper.CartMapper;
import com.glowcorner.backend.repository.CartRepository;
import com.glowcorner.backend.service.interfaces.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    public CartServiceImp(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    // Get Cart by UserID
    @Override
    public CartDTO getCartByUserID(String userID) {
        if(cartRepository.findByUserId(userID).isPresent())
            return cartMapper.toCartDTO(cartRepository.findByUserId(userID).get());
        return null;
    }

    // Add item to Cart
    @Override
    public void addItemToCart(String userID, Product newProduct, int quantity) {
        Cart cart = cartRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Check if the item  already exists in cart
        boolean productExists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProduct().getId().equals(newProduct.getId())) {
                // Update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the cart
        if (!productExists) {
            cart.getItems().add(new CartItem(newProduct, quantity));
        }

        // Save the updated cart
        cartRepository.save(cart);
    }

    // Remove item from Cart
    @Override
    public void removeItemFromCart(String userID, String productID) {
        Cart cart = cartRepository.findByUserId(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productID));

        cartRepository.save(cart);
    }

    // Clear entire Cart
    @Override
    public void clearCart(String userID) {
        Cart cart = cartRepository.findByUserId(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

}
