package com.glowcorner.backend.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String userID;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String skinType;
    private int loyalPoints;

    private String roleID;

    private String authenticationID;

    private List<String> cartIDs;

    private List<String> orderIDs;
}
