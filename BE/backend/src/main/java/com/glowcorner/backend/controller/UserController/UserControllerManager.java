package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByManager;
import com.glowcorner.backend.service.interfaces.UserService;
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
    public ResponseEntity<UserDTOByManager> getUserById(@PathVariable String userId) {
        UserDTOByManager user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDTOByManager> createUser(@RequestBody UserDTOByManager userDTOByManager) {
        UserDTOByManager createdUser = userService.createUser(userDTOByManager);
        return ResponseEntity.ok(createdUser);
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTOByManager> updateUserByManager(@PathVariable String userId, @RequestBody UserDTOByManager userDTO) {
        UserDTOByManager updatedUser = userService.updateUserByManager(userId, userDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
