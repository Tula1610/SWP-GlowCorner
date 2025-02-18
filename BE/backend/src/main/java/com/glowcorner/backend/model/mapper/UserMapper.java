package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.User;
import com.glowcorner.backend.model.DTO.UserDTO;
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
}
