package com.glowcorner.backend.controller.CartController;

import com.glowcorner.backend.model.DTO.Cart.CartDTO;
import com.glowcorner.backend.model.DTO.Cart.CartItemDTO;
import com.glowcorner.backend.service.interfaces.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /* Cart */

    // Get cart for user
    @GetMapping("/{userID}")
    public ResponseEntity<CartDTO> getCartsByUserID(@PathVariable String userID) {
        CartDTO cart = cartService.getCartByUserID(userID);
        if (cart == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Add an item to a cart
    @PostMapping("/{userID}/add")
    public ResponseEntity<String> addItemToCart(@PathVariable String userID, @RequestBody String productID, @RequestParam int quantity) {
        cartService.addItemToCart(userID, productID, quantity);
        return ResponseEntity.ok("Item added to the cart");
    }

    // Remove item from Cart
    @DeleteMapping("{userID}/remove/{productID}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable String userID, @PathVariable String productID) {
        cartService.removeItemFromCart(userID, productID);
        return ResponseEntity.ok("Item removed from the cart");
    }

    // Clear a Cart
    @DeleteMapping("/{userID}/clear")
    public ResponseEntity<String> clearCart(@PathVariable String userID) {
        cartService.clearCart(userID);
        return ResponseEntity.ok("Cart cleared");
    }

    /* Cart Item */

    // Get a Cart Item
    @GetMapping("/{userID}/{productID}")
    public ResponseEntity<CartItemDTO> getCartItem(@PathVariable String userID, @PathVariable String productID) {
        CartItemDTO cartItem = cartService.getCartItem(userID, productID);
        if (cartItem == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartItem);
    }

    // Update a Cart Item
    @PutMapping("/{userID}/{productID}")
    public ResponseEntity<String> updateCartItem(@PathVariable String userID, @PathVariable String productID, @RequestParam int quantity) {
        cartService.updateCartItem(userID, productID, quantity);
        return ResponseEntity.ok("Cart item updated");
    }

}
