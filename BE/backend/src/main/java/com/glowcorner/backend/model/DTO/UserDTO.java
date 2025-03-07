package com.glowcorner.backend.model.DTO;


import com.glowcorner.backend.entity.Authentication;
import com.glowcorner.backend.entity.mongoDB.Cart;
import com.glowcorner.backend.entity.mongoDB.Order;
import com.glowcorner.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDTO {
    String userID;
    String fullName;
    String email;
    String phone;
    String address;
    String skinType;
    int loyalPoints;

    Role role;

    Authentication authentication;

    Cart cart;

    List<Order> orders;
}
