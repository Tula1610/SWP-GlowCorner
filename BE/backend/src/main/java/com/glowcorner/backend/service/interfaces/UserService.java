package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.model.DTO.request.User.CreateCustomerRequest;
import com.glowcorner.backend.model.DTO.request.User.CreateUserRequest;

import java.util.List;

public interface UserService {

    /* Manager */
    UserDTOByManager updateUserByManager(String userID, UserDTOByManager userDTOByManager);
    void deleteUser(String userId);
    UserDTOByManager createUser(CreateUserRequest request);

    List <UserDTOByManager> searchUserByNameManager(String name);
    UserDTOByManager getUserById(String userID);
    List<UserDTOByManager> getAllUsers();

    /* Customer */
    UserDTOByCustomer updateUserByCustomer(String userID, UserDTOByCustomer userDTOByCustomer);
    UserDTOByCustomer createUser(CreateCustomerRequest request);

    /* Beauty Advisor */
//    UserDTOByBeautyAdvisor updateUserByBeautyAdvisor(String userID, UserDTOByBeautyAdvisor userDTOByBeautyAdvisor);

    List<UserDTOByBeautyAdvisor> getAllUsersByBeautyAdvisor();
    UserDTOByBeautyAdvisor getUserByEmailByBeautyAdvisor(String email);
    List<UserDTOByBeautyAdvisor> searchUserByNameBeautyAdvisor(String name);





}
