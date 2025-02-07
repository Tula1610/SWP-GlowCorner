package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.User;
import com.glowcorner.backend.model.mapper.UserMapper;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new Exception("User not found")
        }
        return user;
    }
}
