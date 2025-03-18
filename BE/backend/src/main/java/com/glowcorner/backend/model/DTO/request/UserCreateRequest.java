package com.glowcorner.backend.model.DTO.request;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    String fullName;
    String email;
    String phone;
    String address;
    Role role;
    Category skinType;


}
