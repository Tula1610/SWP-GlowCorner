package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.GoogleLoginDTO;
import com.glowcorner.backend.model.DTO.LoginDTO;
import org.springframework.security.core.Authentication;

import javax.swing.text.html.Option;

public interface AuthenticationService {
    boolean login(String username, String password);
    boolean signup(String username, String password);
//    LoginDTO createAuthentication(String username, String password);
//    String loginWithGoogle(GoogleLoginDTO googleLoginDTO);
}
