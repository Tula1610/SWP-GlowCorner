package com.glowcorner.backend.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int userID;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String skinType;
    private int loyalPoints;
    private int roleID;
}
