package com.glowcorner.backend.model.DTO.User;

import com.glowcorner.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDTOByCustomer {

    String fullName;
    String email;
    String phone;
    String address;
    Category skinType;

}
