package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.*;
import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
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

        return new UserDTO(
                user.getUserID(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getSkinType(),
                user.getLoyalPoints(),
                user.getRole(),
                user.getAuthentication(),
                user.getCart(),
                user.getOrders()
        );
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
        user.setUserID(userDTO.getUserID());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setSkinType(userDTO.getSkinType());
        user.setLoyalPoints(userDTO.getLoyalPoints());
        user.setRole(userDTO.getRole());
        user.setAuthentication(userDTO.getAuthentication());
        user.setCart(userDTO.getCart());
        user.setOrders(userDTO.getOrders());

        return user;
    }
}
