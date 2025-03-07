package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO updateUser(String userID, UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(String userID);
}
