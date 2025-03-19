package com.glowcorner.backend.controller;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.enums.Role;
import com.glowcorner.backend.model.DTO.LoginDTO;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.service.interfaces.AuthenticationService;
import com.glowcorner.backend.utils.JwtUtilHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Authentication Management System", description = "Operations pertaining to authentication in the Authentication Management System")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final JwtUtilHelper jwtUtilHelper;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Operation(summary = "Login", description = "Authenticate user credentials", security = {})
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (!isAuthenticated) {
            return ResponseUtil.error(HttpStatus.UNAUTHORIZED.value(), "Login failed");
        }

        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseUtil.error(HttpStatus.UNAUTHORIZED.value(), "User not found");
        }

        User user = userOpt.get();
        String fullName = user.getFullName() != null ? user.getFullName() : "N/A";
        String role = user.getRole().name();
        String jwtToken = jwtUtilHelper.generateToken(loginDTO.getUsername(), role);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("email", loginDTO.getUsername());
        responseData.put("fullName", fullName);
        responseData.put("role", role);
        responseData.put("jwtToken", jwtToken);

        return ResponseUtil.success(responseData);
    }

    @GetMapping("/oauth2/callback")
    public ResponseEntity<?> oauth2Callback(@RequestParam("code") String code) {
        try {
            Map<String, String> tokenResponse = getGoogleAccessToken(code);
            if (tokenResponse == null || !tokenResponse.containsKey("access_token")) {
                return ResponseUtil.error(HttpStatus.UNAUTHORIZED.value(), "Failed to obtain access token");
            }

            String accessToken = tokenResponse.get("access_token");
            Map<String, Object> userInfo = getGoogleUserInfo(accessToken);
            if (userInfo == null || !userInfo.containsKey("email")) {
                return ResponseUtil.error(HttpStatus.UNAUTHORIZED.value(), "Failed to obtain user info");
            }

            String email = (String) userInfo.get("email");
            String fullName = userInfo.containsKey("name") ? (String) userInfo.get("name") : "N/A";
            User user = findOrCreateUser(email, fullName);

            String role = user.getRole().name();
            String jwtToken = jwtUtilHelper.generateToken(email, role);

            String frontendRedirectUrl = buildFrontendRedirectUrl(jwtToken, role, email, fullName);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(frontendRedirectUrl))
                    .build();

        } catch (Exception e) {
            return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error during Google login: " + e.getMessage());
        }
    }

    private Map<String, String> getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("code", code);
        tokenRequest.put("client_id", clientId);
        tokenRequest.put("client_secret", clientSecret);
        tokenRequest.put("redirect_uri", redirectUri);
        tokenRequest.put("grant_type", "authorization_code");

        return restTemplate.postForObject("https://oauth2.googleapis.com/token", tokenRequest, Map.class);
    }

    private Map<String, Object> getGoogleUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
        return restTemplate.getForObject(userInfoUrl, Map.class);
    }

    private User findOrCreateUser(String email, String fullName) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setRole(Role.CUSTOMER);
            return userRepository.save(user);
        }
        return userOpt.get();
    }

    private String buildFrontendRedirectUrl(String jwtToken, String role, String email, String fullName) throws Exception {
        String encodedJwtToken = URLEncoder.encode(jwtToken, StandardCharsets.UTF_8.toString());
        String encodedRole = URLEncoder.encode(role, StandardCharsets.UTF_8.toString());
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());
        String encodedFullName = URLEncoder.encode(fullName, StandardCharsets.UTF_8.toString());

        return UriComponentsBuilder
                .fromUriString("http://localhost:3000/callback")
                .queryParam("jwtToken", encodedJwtToken)
                .queryParam("role", encodedRole)
                .queryParam("email", encodedEmail)
                .queryParam("fullName", encodedFullName)
                .build()
                .toUriString();
    }

    private static class ResponseUtil {
        static ResponseEntity<ResponseData> success(Object data) {
            return ResponseEntity.ok(new ResponseData(200, true, "Success", data, null, null));
        }

        static ResponseEntity<ResponseData> error(int status, String message) {
            return ResponseEntity.status(status).body(new ResponseData(status, false, message, null, null, null));
        }
    }
}