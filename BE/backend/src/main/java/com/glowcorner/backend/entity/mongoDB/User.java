package com.glowcorner.backend.entity.mongoDB;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    String id;

    String userID;

    String fullName;

    String email;

    String phone;

    String address;

    Category skinType;

    int loyalPoints;

    //One to one
    Role role;

    //One to One
    Authentication authentication;

    //One to one
    Cart cart;

    //One to Many
    List<Order> orders;



}
