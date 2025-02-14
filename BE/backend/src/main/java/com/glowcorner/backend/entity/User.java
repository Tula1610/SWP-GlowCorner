package com.glowcorner.backend.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private ObjectId userID;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String skinType;

    private int loyalPoints;


    private Role role;

    private Authentication authentication;

    private Cart cart;

    private List<Order> orders;


}
