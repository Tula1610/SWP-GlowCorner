package com.glowcorner.backend.controller.AuthenticationController;

import com.glowcorner.backend.model.DTO.LoginDTO;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Management System", description = "Operations pertaining to authentication in the Authentication Management System")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // Login
    @Operation(summary = "Login", description = "Authenticate user credentials")
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authenticationService.login(loginDTO.getUsername(), loginDTO.getPasswordHash());
        if (isAuthenticated) {
            return ResponseEntity.ok(new ResponseData(200, true, "Login successful", null, null, null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(401, false, "Login failed", null, null, null));
        }
    }

}
