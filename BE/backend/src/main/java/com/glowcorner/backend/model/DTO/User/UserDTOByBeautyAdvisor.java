package com.glowcorner.backend.model.DTO.User;

import com.glowcorner.backend.enums.SkinType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserDTOByBeautyAdvisor {

    String fullName;
    String email;
    String phone;
    SkinType skinType;

}
