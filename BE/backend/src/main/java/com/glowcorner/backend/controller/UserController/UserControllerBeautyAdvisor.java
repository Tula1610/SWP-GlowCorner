package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByBeautyAdvisor;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management System (Beauty Advisor)", description = "Operations pertaining to users in the User Management System")
@RestController
@RequestMapping("/api/beautyAdvisor/users")
public class UserControllerBeautyAdvisor {

    private final UserService userService;

    public UserControllerBeautyAdvisor(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<List<UserDTOByBeautyAdvisor>> getAllUsers() {
        List<UserDTOByBeautyAdvisor> users = userService.getAllUsersByBeautyAdvisor();
        return ResponseEntity.ok(users);
    }

    // Get user by email
    @Operation(summary = "Get a user by email", description = "Retrieve a single user using its email")
    @GetMapping("/{email}")
    public ResponseEntity<ResponseData> getUserByEmailByBeautyAdvisor(@PathVariable String email) {
        UserDTOByBeautyAdvisor user = userService.getUserByEmailByBeautyAdvisor(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "User with email: " + email + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "User found", user, null, null));
    }

    // Search user by name
    @Operation(summary = "Search a user by name", description = "Search for a user using their name")
    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseData> searchUserByNameBeautyAdvisor(@PathVariable String name) {
        List<UserDTOByBeautyAdvisor> users = userService.searchUserByNameBeautyAdvisor(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "There is no user with name '" + name + "'.", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "User found", users, null, null));
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
