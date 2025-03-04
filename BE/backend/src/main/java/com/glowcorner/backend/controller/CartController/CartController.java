package com.glowcorner.backend.controller.CartController;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.CartItem;
import com.glowcorner.backend.service.interfaces.CartService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Get all carts for user
    @GetMapping("/{userID}")
    public ResponseEntity<List<Cart>> getCartsByUserID(@PathVariable ObjectId userID) {
        List<Cart> carts = cartService.getCartByUserID(userID);
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartID}")
    public ResponseEntity<Cart> getCartByCartID(@PathVariable ObjectId cartID) {
        Cart cart = cartService.getCartByCartID(cartID);
        return ResponseEntity.ok(cart);
    }

    // Add an item to a cart
    @PostMapping("/{cartID}/add")
    public ResponseEntity<String> addItemToCart(@PathVariable ObjectId cartID, @RequestBody CartItem newItem) {
        cartService.addItemToCart(cartID, newItem);
        return ResponseEntity.ok("Item added to the cart");
    }

    // Remove item from Cart
    @DeleteMapping("{{cartID}/remove/{productID}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable ObjectId cartID, @PathVariable ObjectId productID) {
        cartService.removeItemFromCart(cartID, productID);
        return ResponseEntity.ok("Item removed from the cart");
    }

    // Clear a Cart
    @DeleteMapping("/{cartID}/clear")
    public ResponseEntity<String> clearCart(@PathVariable ObjectId cartID) {
        cartService.clearCart(cartID);
        return ResponseEntity.ok("Cart cleared");
    }

}
