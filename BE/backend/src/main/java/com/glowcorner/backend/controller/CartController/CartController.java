package com.glowcorner.backend.controller.CartController;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.CartDTO;
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
    public ResponseEntity<String> addItemToCart(@PathVariable String userID, @RequestBody Product newProduct, @RequestParam int quantity) {
        cartService.addItemToCart(userID, newProduct, quantity);
        return ResponseEntity.ok("Item added to the cart");
    }

    // Remove item from Cart
    @DeleteMapping("{cartID}/remove/{productID}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable String cartID, @PathVariable String productID) {
        cartService.removeItemFromCart(cartID, productID);
        return ResponseEntity.ok("Item removed from the cart");
    }

    // Clear a Cart
    @DeleteMapping("/{cartID}/clear")
    public ResponseEntity<String> clearCart(@PathVariable String cartID) {
        cartService.clearCart(cartID);
        return ResponseEntity.ok("Cart cleared");
    }

}
