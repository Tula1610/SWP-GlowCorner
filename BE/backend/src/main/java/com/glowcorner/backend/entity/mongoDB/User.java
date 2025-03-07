package com.glowcorner.backend.entity.mongoDB;



import com.glowcorner.backend.entity.Authentication;
import com.glowcorner.backend.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    String userID;

    String fullName;

    String email;

    String phone;

    String address;

    String skinType;

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
