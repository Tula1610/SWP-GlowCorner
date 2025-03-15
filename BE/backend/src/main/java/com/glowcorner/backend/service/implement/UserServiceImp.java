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
        if(userRepository.findByUserID(userId).isPresent())
            return userMapperManager.toUserDTO(userRepository.findByUserID(userId).get());
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
        try{
            User existingUser = userRepository.findByUserID(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            //Update
            if(userDTOByManager.getFullName() != null) existingUser.setFullName(userDTOByManager.getFullName());
            if(userDTOByManager.getEmail() != null) existingUser.setEmail(userDTOByManager.getEmail());
            if(userDTOByManager.getPhone() != null) existingUser.setPhone(userDTOByManager.getPhone());
            if(userDTOByManager.getAddress() != null) existingUser.setAddress(userDTOByManager.getAddress());
            if(userDTOByManager.getSkinType() != null) existingUser.setSkinType(userDTOByManager.getSkinType());
            if(userDTOByManager.getRole() != null) existingUser.setRole(userDTOByManager.getRole());
            if (userDTOByManager.getOrders() != null) existingUser.setOrders(userDTOByManager.getOrders());

            //Save update
            User updatedUser = userRepository.save(existingUser);

            //Convert updated user entity to DTO
            return userMapperManager.toUserDTO(updatedUser);
        } catch (Exception e) {
            throw  new RuntimeException("Fail to update user: " + e.getMessage(), e);
        }
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
        try {
            //Find existing user
            User existingUser = userRepository.findByUserID(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            //Update
            if(userDTOByCustomer.getFullName() != null) existingUser.setFullName(userDTOByCustomer.getFullName());
            if(userDTOByCustomer.getEmail() != null) existingUser.setEmail(userDTOByCustomer.getEmail());
            if(userDTOByCustomer.getPhone() != null) existingUser.setPhone(userDTOByCustomer.getPhone());
            if(userDTOByCustomer.getAddress() != null) existingUser.setAddress(userDTOByCustomer.getAddress());
            if(userDTOByCustomer.getSkinType() != null) existingUser.setSkinType(userDTOByCustomer.getSkinType());

            //Save update
            User updatedUser = userRepository.save(existingUser);

            //Convert updated user entity to DTO
            return userMapperCustomer.toUserDTO(updatedUser);
        } catch (Exception e) {
            throw  new RuntimeException("Fail to update user: " + e.getMessage(), e);
        }
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
        if(!email.isEmpty())
            return userMapperBeautyAdvisor.toUserDTO(userRepository.findByEmail(email));
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
