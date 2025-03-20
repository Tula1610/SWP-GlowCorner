package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.model.DTO.request.User.CreateCustomerRequest;
import com.glowcorner.backend.model.DTO.request.User.CreateUserRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.User.CreateCustomerRequestMapper;
import com.glowcorner.backend.model.mapper.User.*;
import com.glowcorner.backend.repository.CartRepository;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final UserMapperManager userMapperManager;

    private final UserMapperCustomer userMapperCustomer;

    private final UserMapperBeautyAdvisor userMapperBeautyAdvisor;

    private final UserCreateRequestMapper userCreateRequestMapper;

    private final CreateCustomerRequestMapper customerCreateRequestMapper;

    public UserServiceImp(UserRepository userRepository, UserMapperManager userMapperManager, UserMapperCustomer userMapperCustomer, UserMapperBeautyAdvisor userMapperBeautyAdvisor, UserCreateRequestMapper userCreateRequestMapper, CreateCustomerRequestMapper customerCreateRequestMapper, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.userMapperManager = userMapperManager;
        this.userMapperCustomer = userMapperCustomer;
        this.userMapperBeautyAdvisor = userMapperBeautyAdvisor;
        this.userCreateRequestMapper = userCreateRequestMapper;
        this.customerCreateRequestMapper = customerCreateRequestMapper;
        this.cartRepository = cartRepository;
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
    public UserDTOByManager createUser(CreateUserRequest request) {
        Cart newCart = new Cart();

        User user = userCreateRequestMapper.fromCreateRequest(request);
        user.setCart(newCart);
        user = userRepository.save(user);
        return userMapperManager.toUserDTO(user);
    }

    //Manager update a user
    @Override
    public UserDTOByManager updateUserByManager(String userID, UserDTOByManager userDTOByManager) {
        //Find existing user
        try{
            User existingUser = userRepository.findByUserID(userID)
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
    public void deleteUser(String userID) {
        cartRepository.deleteByUserID(userID);
        userRepository.deleteUserByUserID(userID);
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

    // Create a customer account
    @Override
    public UserDTOByCustomer createUser(CreateCustomerRequest request) {
        User user = customerCreateRequestMapper.fromCreateRequest(request);
        user = userRepository.save(user);
        return userMapperCustomer.toUserDTO(user);
    }

    // Users update themselves
    @Override
    public UserDTOByCustomer updateUserByCustomer(String userID, UserDTOByCustomer userDTOByCustomer) {
        try {
            //Find existing user
            User existingUser = userRepository.findByUserID(userID)
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
        if (!email.isEmpty()) {
            User user = userRepository.findByEmail(email).orElse(null);
            return userMapperBeautyAdvisor.toUserDTO(user);
        }
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
