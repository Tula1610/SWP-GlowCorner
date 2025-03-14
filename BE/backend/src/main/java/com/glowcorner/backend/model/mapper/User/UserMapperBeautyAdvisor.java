package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserMapperBeautyAdvisor {

    private final CounterServiceImpl counterServiceImpl;

    public UserMapperBeautyAdvisor(CounterServiceImpl counterServiceImpl) {
        this.counterServiceImpl = counterServiceImpl;
    }

    public UserDTOByBeautyAdvisor toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTOByBeautyAdvisor(
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getSkinType()
        );
    }

    public User toUserByBeautyAdvisor(UserDTOByBeautyAdvisor userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setUserID(counterServiceImpl.getNextUserID());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setSkinType(userDTO.getSkinType());

        return user;
    }

}
