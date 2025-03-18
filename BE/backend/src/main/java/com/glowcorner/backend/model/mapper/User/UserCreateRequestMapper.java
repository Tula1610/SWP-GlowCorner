package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.request.UserCreateRequest;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestMapper {

    private final CounterServiceImpl counterServiceImpl;

    public UserCreateRequestMapper(CounterServiceImpl counterServiceImpl) {
        this.counterServiceImpl = counterServiceImpl;
    }

    public User fromCreateRequest(UserCreateRequest request) {
        if (request == null){
            return null;
        }

        User user = new User();
        user.setUserID(counterServiceImpl.getNextUserID());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        user.setSkinType(request.getSkinType());
        return user;
    }

}
