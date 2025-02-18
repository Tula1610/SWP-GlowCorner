package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.*;
import com.glowcorner.backend.model.DTO.UserDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .userID(user.getUserID().toHexString()) // Convert ObjectId to String
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .skinType(user.getSkinType())
                .loyalPoints(user.getLoyalPoints())
                .roleID(user.getRole() != null ? user.getRole().getRoleID().toHexString() : null)
                .authenticationID(user.getAuthentication() != null ? user.getAuthentication().getAuthenticationTokenID().toHexString() : null)
                .cartIDs(user.getCart() != null ? user.getCart().stream().map(cart -> cart.getCartID().toHexString()).collect(Collectors.toList()) : null)
                .orderIDs(user.getOrders() != null ? user.getOrders().stream().map(order -> order.getOrderID().toHexString()).collect(Collectors.toList()) : null)
                .build();
    }

    public List<UserDTO> toUserDTO(List<User> users) {
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    // Convert UserDTO to User entity
    public User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setUserID(new ObjectId(userDTO.getUserID()));  // Convert String back to ObjectId
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setSkinType(userDTO.getSkinType());
        user.setLoyalPoints(userDTO.getLoyalPoints());

        // Map Role
        if (userDTO.getRoleID() != null) {
            Role role = new Role();
            role.setRoleID(new ObjectId(userDTO.getRoleID()));  // Assuming Role has a roleID field
            user.setRole(role);
        }

        // Map Authentication
        if (userDTO.getAuthenticationID() != null) {
            Authentication authentication = new Authentication();
            authentication.setAuthenticationTokenID(new ObjectId(userDTO.getAuthenticationID()));  // Assuming Authentication has an ID field
            user.setAuthentication(authentication);
        }

        // Map Cart (List of Cart entities)
        if (userDTO.getCartIDs() != null) {
            List<Cart> carts = userDTO.getCartIDs().stream()
                    .map(cartID -> {
                        Cart cart = new Cart();
                        cart.setCartID(new ObjectId(cartID));  // Assuming Cart has a cartID field
                        return cart;
                    })
                    .collect(Collectors.toList());
            user.setCart(carts);
        }

        // Map Orders (List of Order entities)
        if (userDTO.getOrderIDs() != null) {
            List<Order> orders = userDTO.getOrderIDs().stream()
                    .map(orderID -> {
                        Order order = new Order();
                        order.setOrderID(new ObjectId(orderID));  // Assuming Order has an orderID field
                        return order;
                    })
                    .collect(Collectors.toList());
            user.setOrders(orders);
        }

        return user;
    }
}
