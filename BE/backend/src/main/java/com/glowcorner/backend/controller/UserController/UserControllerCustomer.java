package com.glowcorner.backend.controller.UserController;

import com.glowcorner.backend.model.DTO.User.UserDTOByCustomer;
import com.glowcorner.backend.model.DTO.request.User.CreateCustomerRequest;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Management System (Customer)", description = "Operations pertaining to users in the User Management System")
@RestController
@RequestMapping("/api/user")
public class UserControllerCustomer {

    private final UserService userService;

    public UserControllerCustomer (UserService userService){
        this.userService = userService;
    }

//    // Create user
//    @Operation(summary = "Create a new customer", description = "Add a new customer to the system")
//    @PostMapping
//    public ResponseEntity<ResponseData> createUser(@RequestBody CreateCustomerRequest request) {
//        UserDTOByCustomer createdUser = userService.createUser(request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new ResponseData(201, true, "User created", createdUser, null, null));
//    }

    // Update user
    @Operation(summary = "Update a user by ID", description = "Update a user using its ID")
    @PutMapping("{userId}")
    public ResponseEntity<ResponseData> updateUserByCustomer(@PathVariable String userId, @RequestBody UserDTOByCustomer userDTO) {
        try {
            UserDTOByCustomer updatedUser = userService.updateUserByCustomer(userId, userDTO);
            if (updatedUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "User with ID: " + userId + " not found", null, null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "User updated", updatedUser, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(400, false, e.getMessage(), null, null, null));
        }
    }
}
