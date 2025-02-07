package com.glowcorner.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int userID;

    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String skinType;
    private int loyalPoints;
    private int roleID;

}
