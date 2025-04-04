package com.glowcorner.backend.controller.UserController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.model.DTO.request.User.CreateCustomerRequest;
import com.glowcorner.backend.model.DTO.request.User.CreateUserRequest;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.CloudinaryService;
import com.glowcorner.backend.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User Management System (Customer)", description = "Operations pertaining to users in the User Management System")
@RestController
@RequestMapping("/api/user")
public class UserControllerCustomer {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public UserControllerCustomer(UserService userService, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    // Create user
    @Operation(summary = "Create a new customer", description = "Add a new customer to the system")
    @PostMapping
    public ResponseEntity<ResponseData> createUser(@RequestBody CreateCustomerRequest request) {
        UserDTOByCustomer createdUser = userService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData(201, true, "User created", createdUser, null, null));
    }

    // Update user
    @Operation(summary = "Update a user by ID", description = "Update a user using its ID")
    @PutMapping(value = "{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseData> updateUserByCustomer(
            @PathVariable String userId,
            @RequestPart(value = "user", required = false) @Parameter(
                    description = "User data in JSON format",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateUserRequest.class)
                    )
            ) String userJson,
            @RequestPart(value = "image", required = false) @Parameter(
                    description = "User's avatar image file",
                    content = @Content(mediaType = MediaType.IMAGE_PNG_VALUE)
            ) MultipartFile imageFile) {
        try {
            UserDTOByCustomer userDTO;
            if (userJson != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                userDTO = objectMapper.readValue(userJson, UserDTOByCustomer.class);
            } else {
                userDTO = userService.getUserByIdForCustomer(userId);
                if (userDTO == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseData(404, false, "User with ID: " + userId + " not found", null, null, null));
                }
            }

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(imageFile);
                userDTO.setAvatar_url(imageUrl);
            }

            UserDTOByCustomer updatedUser = userService.updateUserByCustomer(userId, userDTO);
            return ResponseEntity.ok(new ResponseData(200, true, "User updated", updatedUser, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update user: " + e.getMessage(), null, null, null));
        }
    }

    // Get customer by id
    @Operation(summary = "Get a customer by ID", description = "Retrieve a single customer using its ID")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        UserDTOByCustomer user = userService.getUserByIdForCustomer(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "User with ID: " + userId + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "User found", user, null, null));
    }
}