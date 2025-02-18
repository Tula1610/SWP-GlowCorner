package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.UserDTO;
import com.glowcorner.backend.service.interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Get all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable ObjectId userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable ObjectId userId) {
        return userService.getUserById(userId);
    }
}
