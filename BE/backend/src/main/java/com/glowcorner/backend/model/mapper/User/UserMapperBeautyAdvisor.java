package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import org.springframework.stereotype.Component;

@Component
public class UserMapperBeautyAdvisor {

    public UserDTOByBeautyAdvisor toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTOByBeautyAdvisor(
                user.getUserID(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getSkinType()
        );
    }

}
