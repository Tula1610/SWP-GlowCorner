package com.glowcorner.backend.entity.mongoDB;



import com.glowcorner.backend.entity.Authentication;
import com.glowcorner.backend.enums.Role;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@Builder
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

    //One to one
    private Role role;

    //One to One
    private Authentication authentication;

    //One to Many
    private List<Cart> cart;

    //One to Many
    private List<Order> orders;


}
