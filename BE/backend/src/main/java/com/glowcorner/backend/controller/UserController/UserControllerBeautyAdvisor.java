package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautyAdvisor/users")
public class UserControllerBeautyAdvisor {

    private final UserService userService;

    public UserControllerBeautyAdvisor(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTOByBeautyAdvisor>> getAllUsers() {
        List<UserDTOByBeautyAdvisor> users = userService.getAllUsersByBeautyAdvisor();
        return ResponseEntity.ok(users);
    }

    // Get user by email
    @GetMapping("/{email}")
    public ResponseEntity<UserDTOByBeautyAdvisor> getUserByEmailByBeautyAdvisor(@PathVariable String email) {
        UserDTOByBeautyAdvisor user = userService.getUserByEmailByBeautyAdvisor(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Search user by name
    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserDTOByBeautyAdvisor>> searchUserByNameBeautyAdvisor(@PathVariable String name) {
        List<UserDTOByBeautyAdvisor> users = userService.searchUserByNameBeautyAdvisor(name);
        return ResponseEntity.ok(users);
    }

    // Update user
//    @PutMapping("/{userId}")
//    public ResponseEntity<UserDTOByBeautyAdvisor> updateUserByBeautyAdvisor(@PathVariable String userId, @RequestBody UserDTOByBeautyAdvisor userDTO) {
//        UserDTOByBeautyAdvisor updatedUser = userService.updateUserByBeautyAdvisor(userId, userDTO);
//        if (updatedUser == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updatedUser);
//    }

}
