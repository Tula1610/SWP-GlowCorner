package com.glowcorner.backend.controller;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.model.DTO.LoginDTO;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import com.glowcorner.backend.utils.JwtUtilHelper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Authentication Management System", description = "Operations pertaining to authentication in the Authentication Management System")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtilHelper jwtUtilHelper;

    // Login
    @Operation(summary = "Login", description = "Authenticate user credentials", security = {})
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok(new ResponseData(200, true, "Login successful", null, null, null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(401, false, "Login failed", null, null, null));
        }
    }

    @PostMapping("/login/google")
    @Operation(summary = "Login with Google by Email", security = {})
    public ResponseEntity<ResponseData> loginWithGoogle(@RequestParam("email") String email) {
        // Tìm user trong database
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseData(401, false, "User not found", null, null, null));
        }

        // Lấy thông tin user
        User user = userOpt.get();
        String fullName = user.getFullName() != null ? user.getFullName() : "N/A";
        String role = user.getRole().name();

        // Tạo JWT Token
        String jwtToken = jwtUtilHelper.generateToken(email, role);

        // Trả về response
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("email", email);
        responseData.put("fullName", fullName);
        responseData.put("role", role);
        responseData.put("jwtToken", jwtToken);

        return ResponseEntity.ok(new ResponseData(200, true, "Login successful", responseData, null, null));
    }

    @PostMapping("/login/token/google")
    @Operation(summary = "Login with Google by JWT Token", security = {})
    public ResponseEntity<ResponseData> loginWithGoogleByToken(@RequestParam("jwtToken") String token) {
        // Kiểm tra token hợp lệ
        if (!jwtUtilHelper.verifyToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseData(401, false, "Invalid or expired token", null, null, null));
        }

        // Lấy email từ token
        String email = jwtUtilHelper.getUsernameFromToken(token);

        // Tìm user trong database
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseData(401, false, "User not found", null, null, null));
        }

        // Lấy thông tin user
        User user = userOpt.get();
        String fullName = user.getFullName() != null ? user.getFullName() : "N/A";
        String role = user.getRole().name();

        // Trả về response
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("email", email);
        responseData.put("fullName", fullName);
        responseData.put("role", role);
        responseData.put("jwtToken", token);

        return ResponseEntity.ok(new ResponseData(200, true, "Login successful", responseData, null, null));
    }

    @PostMapping("/login/create")
    @Operation(summary = "Create Authentication", description = "Create a new authentication", security = {})
    public ResponseEntity<ResponseData> createAuthentication(@RequestBody LoginDTO loginDTO) {
        LoginDTO newLoginDTO = authenticationService.createAuthentication(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity.ok(new ResponseData(200, true, "Create authentication successful", newLoginDTO, null, null));
    }

    @Hidden
    @GetMapping(value = "/login/google", produces = MediaType.TEXT_HTML_VALUE)
    public String loginWithGoogle(
            @RequestParam("email") String email,
            @RequestParam("role") String role,
            @RequestParam("jwtToken") String jwtToken) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "<html><body><h1>Error</h1><p>User not found</p></body></html>";
        }

        User user = userOpt.get();
        String fullName = user.getFullName() != null ? user.getFullName() : "N/A";

        // Trả về HTML
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Login Success</title></head>" +
                "<body>" +
                "<h1>Login Successful!</h1>" +
                "<p>Email: " + email + "</p>" +
                "<p>Full Name: " + fullName + "</p>" +
                "<p>Role: " + role + "</p>" +
                "<p>JWT Token: " + jwtToken + "</p>" +
                "</body>" +
                "</html>";
    }
}