package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.UserDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO updateUser(ObjectId userID, UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(ObjectId userID);
}
