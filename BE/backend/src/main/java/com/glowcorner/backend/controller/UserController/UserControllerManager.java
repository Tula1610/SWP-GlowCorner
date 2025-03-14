package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/users")
public class UserControllerManager {

    private final UserService userService;

    public UserControllerManager(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTOByManager>> getAllUsers() {
        List<UserDTOByManager> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by id
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
    @PostMapping
    public ResponseEntity<ResponseData> createUser(@RequestBody UserDTOByManager userDTOByManager) {
        UserDTOByManager createdUser = userService.createUser(userDTOByManager);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData(201, true, "User created", createdUser, null, null));
    }

    // Update user
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
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
