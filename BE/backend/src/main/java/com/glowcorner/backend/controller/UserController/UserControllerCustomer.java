package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserControllerCustomer {

    private final UserService userService;

    public UserControllerCustomer (UserService userService){
        this.userService = userService;
    }

    // Update user
    @PutMapping("{userId}")
    public ResponseEntity<UserDTOByCustomer> updateUserByCustomer(@PathVariable String userId, @RequestBody UserDTOByCustomer userDTO) {
        UserDTOByCustomer updatedUser = userService.updateUserByCustomer(userId, userDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

}
