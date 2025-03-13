package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.model.mapper.User.UserMapperBeautyAdvisor;
import com.glowcorner.backend.model.mapper.User.UserMapperCustomer;
import com.glowcorner.backend.model.mapper.User.UserMapperManager;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapperManager userMapperManager;

    private final UserMapperCustomer userMapperCustomer;

    private final UserMapperBeautyAdvisor userMapperBeautyAdvisor;

    public UserServiceImp(UserRepository userRepository, UserMapperManager userMapperManager, UserMapperCustomer userMapperCustomer, UserMapperBeautyAdvisor userMapperBeautyAdvisor) {
        this.userRepository = userRepository;
        this.userMapperManager = userMapperManager;
        this.userMapperCustomer = userMapperCustomer;
        this.userMapperBeautyAdvisor = userMapperBeautyAdvisor;
    }

    /* Manager */

    // Get all users
    @Override
    public List<UserDTOByManager> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapperManager::toUserDTO)
                .toList();
    }

    // Get user by ID
    @Override
    public UserDTOByManager getUserById(String userId) {
        if(userRepository.findByUserId(userId).isPresent())
            return userMapperManager.toUserDTO(userRepository.findByUserId(userId).get());
        return null;
    }

    // Create a new user
    @Override
    public UserDTOByManager createUser(UserDTOByManager userDTOByManager) {
        User user = userMapperManager.toUser(userDTOByManager);
        user = userRepository.save(user);
        return userMapperManager.toUserDTO(user);
    }

    //Manager update a user
    @Override
    public UserDTOByManager updateUserByManager(String userId, UserDTOByManager userDTOByManager) {
        //Find existing user
        User existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Update
        existingUser.setFullName(userDTOByManager.getFullName());
        existingUser.setEmail(userDTOByManager.getEmail());
        existingUser.setPhone(userDTOByManager.getPhone());
        existingUser.setAddress(userDTOByManager.getAddress());
        existingUser.setSkinType(userDTOByManager.getSkinType());
        existingUser.setLoyalPoints(userDTOByManager.getLoyalPoints());
        existingUser.setRole(userDTOByManager.getRole());
        existingUser.setOrders(userDTOByManager.getOrders());

        //Save update
        User updatedUser = userRepository.save(existingUser);

        //Convert updated user entity to DTO
        return userMapperManager.toUserDTO(updatedUser);
    }

    // Delete a user
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // Search user by name
    @Override
    public List<UserDTOByManager> searchUserByNameManager(String name) {
        String regex = ".*" + name + ".*";
        List<User> users = userRepository.findByFullNameRegexIgnoreCase(regex);
        return users.stream()
                .map(userMapperManager::toUserDTO)
                .toList();
    }

    /* Customer */

    //User update themselves
    @Override
    public UserDTOByCustomer updateUserByCustomer(String userId, UserDTOByCustomer userDTOByCustomer) {
        //Find existing user
        User existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Update
        existingUser.setFullName(userDTOByCustomer.getFullName());
        existingUser.setEmail(userDTOByCustomer.getEmail());
        existingUser.setPhone(userDTOByCustomer.getPhone());
        existingUser.setAddress(userDTOByCustomer.getAddress());
        existingUser.setSkinType(userDTOByCustomer.getSkinType());

        //Save update
        User updatedUser = userRepository.save(existingUser);

        //Convert updated user entity to DTO
        return userMapperCustomer.toUserDTO(updatedUser);
    }

    /* Beauty Advisor */

    // Get all users
    @Override
    public List<UserDTOByBeautyAdvisor> getAllUsersByBeautyAdvisor() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapperBeautyAdvisor::toUserDTO)
                .toList();
    }

    // Get user by email
    @Override
    public UserDTOByBeautyAdvisor getUserByEmailByBeautyAdvisor(String email) {
        if(userRepository.findByEmail(email).isPresent())
            return userMapperBeautyAdvisor.toUserDTO(userRepository.findByEmail(email).get());
        return null;
    }

    // Get user by name
    public List<UserDTOByBeautyAdvisor> searchUserByNameBeautyAdvisor(String name) {
        String regex = ".*" + name + ".*";
        List<User> users = userRepository.findByFullNameRegexIgnoreCase(regex);
        return users.stream()
                .map(userMapperBeautyAdvisor::toUserDTO)
                .toList();
    }

    // Update a user
//    @Override
//    public UserDTOByBeautyAdvisor updateUserByBeautyAdvisor(String userId, UserDTOByBeautyAdvisor userDTOByBeautyAdvisor) {
//        //Find existing user
//        User existingUser = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        //Update
//        existingUser.setFullName(userDTOByBeautyAdvisor.getFullName());
//        existingUser.setEmail(userDTOByBeautyAdvisor.getEmail());
//        existingUser.setPhone(userDTOByBeautyAdvisor.getPhone());
//        existingUser.setSkinType(userDTOByBeautyAdvisor.getSkinType());
//
//        //Save update
//        User updatedUser = userRepository.save(existingUser);
//
//        //Convert updated user entity to DTO
//        return userMapperBeautyAdvisor.toUserDTO(updatedUser);
//    }
}
