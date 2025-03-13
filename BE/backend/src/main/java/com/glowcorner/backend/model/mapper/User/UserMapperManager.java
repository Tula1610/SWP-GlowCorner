package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperManager {

    public UserDTOByManager toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTOByManager(
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getSkinType(),
                user.getLoyalPoints(),
                user.getRole(),
                user.getOrders()
        );
    }

    public List<UserDTOByManager> toUserDTO(List<User> users) {
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }

    // Convert UserDTO to User entity
    public User toUser(UserDTOByManager userDTOByManager) {
        if (userDTOByManager == null) {
            return null;
        }

        User user = new User();
        user.setFullName(userDTOByManager.getFullName());
        user.setEmail(userDTOByManager.getEmail());
        user.setPhone(userDTOByManager.getPhone());
        user.setAddress(userDTOByManager.getAddress());
        user.setSkinType(userDTOByManager.getSkinType());
        user.setLoyalPoints(userDTOByManager.getLoyalPoints());
        user.setRole(userDTOByManager.getRole());
        user.setOrders(userDTOByManager.getOrders());

        return user;
    }
}
