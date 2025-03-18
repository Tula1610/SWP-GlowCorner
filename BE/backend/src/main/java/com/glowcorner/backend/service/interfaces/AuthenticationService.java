package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.GoogleLoginDTO;

public interface AuthenticationService {
    boolean login(String username, String password);
    String loginWithGoogle(GoogleLoginDTO googleLoginDTO);
}
