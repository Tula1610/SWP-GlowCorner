package com.glowcorner.backend.model.DTO;

import lombok.Data;

@Data
public class GoogleLoginDTO {
    private String email;
    private String fullName;
    private String idToken;
}
