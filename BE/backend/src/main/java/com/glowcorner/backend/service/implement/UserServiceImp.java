package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.User;
import com.glowcorner.backend.model.DTO.UserDTO;
import com.glowcorner.backend.model.mapper.UserMapper;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTO(users);
    }

    @Override
    public UserDTO updateUser(ObjectId userId, UserDTO userDTO) {
        //Find existing user
        User existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Update
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setAddress(userDTO.getAddress());

        //Save update
        User updatedUser = userRepository.save(existingUser);

        //Convert updated user entity to DTO
        return userMapper.toUserDTO(updatedUser);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User createdUser = userRepository.save(user);
        return userMapper.toUserDTO(createdUser);
    }

    @Override
    public UserDTO getUserById(ObjectId userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserDTO(user);
    }
}
