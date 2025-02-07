package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;


}
