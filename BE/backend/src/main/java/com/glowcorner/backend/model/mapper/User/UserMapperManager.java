package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
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
                user.getUserID(),
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
}
