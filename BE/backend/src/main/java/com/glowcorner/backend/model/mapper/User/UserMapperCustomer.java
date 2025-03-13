package com.glowcorner.backend.model.mapper.User;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import org.springframework.stereotype.Component;

@Component
public class UserMapperCustomer {

    public UserDTOByCustomer toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserDTOByCustomer(
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getSkinType()
        );
    }

//    public List<UserDTOByCustomer> toUserDTO(List<User> users) {
//        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
//    }
//
//    // Convert UserDTO to User entity
//    public User toUser(UserDTOByCustomer userDTOByCustomer) {
//        if (userDTOByCustomer == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setFullName(userDTOByCustomer.getFullName());
//        user.setEmail(userDTOByCustomer.getEmail());
//        user.setPhone(userDTOByCustomer.getPhone());
//        user.setAddress(userDTOByCustomer.getAddress());
//        user.setSkinType(userDTOByCustomer.getSkinType());
//
//        return user;
//    }

}
