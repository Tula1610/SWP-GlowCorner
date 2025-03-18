package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.model.DTO.request.UserCreateRequest;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management System", description = "Operations pertaining to users in the User Management System")
@RestController
@RequestMapping("/manager/users")
public class UserControllerManager {

    private final UserService userService;

    public UserControllerManager(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<List<UserDTOByManager>> getAllUsers() {
        List<UserDTOByManager> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by id
    @Operation(summary = "Get a user by ID", description = "Retrieve a single user using its ID")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        UserDTOByManager user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "User with ID: " + userId + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "User found", user, null, null));
    }

    // Search user by name
    @Operation(summary = "Search a user by name", description = "Search for a user using their name")
    @GetMapping("/search")
    public ResponseEntity<?> searchUserByName(@RequestParam String name) {
        List<UserDTOByManager> users = userService.searchUserByNameManager(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "There is no user with name '" + name + "'.", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "User found", users, null, null));
    }

    // Create a new user
    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @PostMapping
    public ResponseEntity<ResponseData> createUser(@RequestBody UserCreateRequest request) {
        UserDTOByManager createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData(201, true, "User created", createdUser, null, null));
    }

    // Update user
    @Operation(summary = "Update a user", description = "Update an existing user using their ID")
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseData> updateUserByManager(@PathVariable String userId, @RequestBody UserDTOByManager userDTO) {
        try {
            UserDTOByManager updatedUser = userService.updateUserByManager(userId, userDTO);
            if (updatedUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "User with ID: " + userId + " not found", null, null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "User updated", updatedUser, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Fail to update user with ID: " + userId, null, null, null));
        }

    }

    // Delete user
    @Operation(summary = "Delete a user by ID", description = "Remove a user from the system using their ID")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
