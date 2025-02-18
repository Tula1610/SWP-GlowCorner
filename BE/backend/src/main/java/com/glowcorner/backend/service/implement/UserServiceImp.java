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
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTO(users);
    }

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
}
