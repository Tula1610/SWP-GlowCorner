package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.User;
import com.glowcorner.backend.model.DTO.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getFullName(), user.getEmail(), user.getPhone(), user.getAddress(), user.getSkinType(), user.getLoyalPoints(), user.getRoleID());
    }

    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getFullName(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getAddress(), userDTO.getSkinType(), userDTO.getLoyalPoints(), userDTO.getRoleID());
    }
}
