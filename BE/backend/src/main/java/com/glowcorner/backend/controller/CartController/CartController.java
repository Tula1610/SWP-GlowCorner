package com.glowcorner.backend.controller.CartController;

import com.glowcorner.backend.model.DTO.Cart.CartDTO;
import com.glowcorner.backend.model.DTO.Cart.CartItemDTO;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart Management System", description = "Operations pertaining to cart in the Cart Management System")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /* Cart */

    // Get cart for user
    @Operation(summary = "Get cart by user ID", description = "Retrieve a cart using the user ID")
    @GetMapping("/{userID}")
    public ResponseEntity<CartDTO> getCartsByUserID(@PathVariable String userID) {
        CartDTO cart = cartService.getCartByUserID(userID);
        if (cart == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    // Add an item to a cart
    @Operation(summary = "Add an item to the cart", description = "Add an item to the cart using the user ID and product ID")
    @PostMapping("/{userID}/add/{productID}")
    public ResponseEntity<ResponseData> addItemToCart(@PathVariable String userID, @PathVariable String productID) {
        cartService.addItemToCart(userID, productID);
        return ResponseEntity.ok(new ResponseData(200, true, "Item added to the cart", null, null, null));
    }

    // Remove item from Cart
    @Operation(summary = "Remove an item from the cart", description = "Remove an item from the cart using the user ID and product ID")
    @DeleteMapping("{userID}/remove/{productID}")
    public ResponseEntity<ResponseData> removeItemFromCart(@PathVariable String userID, @PathVariable String productID) {
        cartService.removeItemFromCart(userID, productID);
        if (cartService.getCartItem(userID, productID) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Item not found in the cart", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Item removed from the cart", null, null, null));
    }

    // Clear a Cart
    @Operation(summary = "Clear the cart", description = "Clear the cart using the user ID")
    @DeleteMapping("/{userID}/clear")
    public ResponseEntity<ResponseData> clearCart(@PathVariable String userID) {
        cartService.clearCart(userID);
        return ResponseEntity.ok(new ResponseData(200, true, "Cart cleared", null, null, null));
    }

    /* Cart Item */

    // Get a Cart Item
    @Operation(summary = "Get a cart item", description = "Retrieve a cart item using the user ID and product ID")
    @GetMapping("/{userID}/{productID}")
    public ResponseEntity<ResponseData> getCartItem(@PathVariable String userID, @PathVariable String productID) {
        CartItemDTO cartItem = cartService.getCartItem(userID, productID);
        if (cartItem == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Item not found in the cart", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Item found in the cart", cartItem, null, null));
    }

    // Update a Cart Item
    @PutMapping("/{userID}/{productID}")
    public ResponseEntity<ResponseData> updateCartItem(@PathVariable String userID, @PathVariable String productID, @RequestParam int quantity) {
        cartService.updateCartItem(userID, productID, quantity);
        if (cartService.getCartItem(userID, productID) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Item not found in the cart", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Item updated in the cart", null, null, null));
    }

}
