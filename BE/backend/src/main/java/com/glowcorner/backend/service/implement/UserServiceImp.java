package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;


}
