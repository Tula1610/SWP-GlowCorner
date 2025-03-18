package com.glowcorner.backend.controller.AuthenticationController;

import com.glowcorner.backend.model.DTO.GoogleLoginDTO;
import com.glowcorner.backend.model.DTO.LoginDTO;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authenticationService.login(loginDTO.getUsername(), loginDTO.getPasswordHash());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/login/google")
    public ResponseEntity<String> loginWithGoogle(@RequestBody GoogleLoginDTO googleLoginDTO) {
        String token = authenticationService.loginWithGoogle(googleLoginDTO);
        return ResponseEntity.ok(token);
    }

}
