package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.UserDTO;
import com.glowcorner.backend.model.mapper.UserMapper;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Get all users
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    // Get user by ID
    @Override
    public UserDTO getUserById(String userId) {
        if(userRepository.findByUserId(userId).isPresent())
            return userMapper.toUserDTO(userRepository.findByUserId(userId).get());
        return null;
    }

    // Create a new user
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    //Update a user
    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        //Find existing user
        User existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Update
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setLoyalPoints(userDTO.getLoyalPoints());
        existingUser.setSkinType(userDTO.getSkinType());
        existingUser.setRole(userDTO.getRole());

        //Save update
        User updatedUser = userRepository.save(existingUser);

        //Convert updated user entity to DTO
        return userMapper.toUserDTO(updatedUser);
    }

    // Delete a user
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
