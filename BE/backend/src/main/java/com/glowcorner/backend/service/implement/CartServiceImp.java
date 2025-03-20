package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.mongoDB.CartItem;
import com.glowcorner.backend.model.DTO.Cart.CartDTO;
import com.glowcorner.backend.model.DTO.Cart.CartItemDTO;
import com.glowcorner.backend.model.mapper.Cart.CartItemMapper;
import com.glowcorner.backend.model.mapper.Cart.CartMapper;
import com.glowcorner.backend.repository.CartItemRepository;
import com.glowcorner.backend.repository.CartRepository;
import com.glowcorner.backend.service.interfaces.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final CartMapper cartMapper;

    private final CartItemMapper cartItemMapper;

    public CartServiceImp(CartRepository cartRepository, CartItemRepository cartItemRepository, CartMapper cartMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
    }

    /* Cart */

    // Get Cart by UserID
    @Override
    public CartDTO getCartByUserID(String userID) {
        if(cartRepository.findByUserID(userID).isPresent())
            return cartMapper.toCartDTO(cartRepository.findByUserID(userID).get());
        return null;
    }

    // Add item to Cart
    @Override
    public void addItemToCart(String userID, String productID) {
        Cart cart = cartRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Check if the item  already exists in cart
        boolean productExists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductID().equals(productID)) {
                // Update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
                productExists = true;
                break;
            }
        }

        // If the item does not exist, add it to the cart
        if (!productExists) {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setUserID(userID);
            itemDTO.setQuantity(1);
            itemDTO.setProductID(productID);
            CartItem item = cartItemMapper.toCartItem(itemDTO);
            cartItemRepository.save(item);
            cart.getItems().add(item);
        }

        // Save the updated cart
        cartRepository.save(cart);
    }

    // Remove item from Cart
    @Override
    public void removeItemFromCart(String userID, String productID) {
        Cart cart = cartRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(i -> i.getProductID().equals(productID));

        cartRepository.save(cart);
    }

    // Clear entire Cart
    @Override
    public void clearCart(String userID) {
        Cart cart = cartRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    /* CartItem */

    // Get CartItem by UserID and ProductID
    @Override
    public CartItemDTO getCartItem(String userID, String productID) {
        Cart cart = cartRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductID().equals(productID)) {
                return cartItemMapper.toCartItemDTO(cartItem);
            }
        }

        return null;
    }

    // Update CartItem quantity
    @Override
    public void updateCartItem(String userID, String productID, int quantity) {
        Cart cart = cartRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProductID().equals(productID)) {
                cartItem.setQuantity(quantity);
                cartRepository.save(cart);
                break;
            }
        }
        throw new RuntimeException("Cart item not found");

    }

}
