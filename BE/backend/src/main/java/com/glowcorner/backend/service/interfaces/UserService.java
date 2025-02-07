package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.entity.User;

public interface UserService {
    User getUserById(Integer id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User saveUser(User user);
    User updateUser(User user);
}
