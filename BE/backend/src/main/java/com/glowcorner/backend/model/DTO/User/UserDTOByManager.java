package com.glowcorner.backend.model.DTO.User;


import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDTOByManager {

    String userID;
    String fullName;
    String email;
    String phone;
    String address;
    Category skinType;
    int loyalPoints;

    Role role;

    List<Order> orders;
}
