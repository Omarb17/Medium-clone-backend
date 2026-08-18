package com.Omarb17.medium_clone.controller;
import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Add a new user")
    @PostMapping
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        User savedUser = userService.addNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Operation(summary = "Edit user")
    @PutMapping("/{id}")
    public ResponseEntity<User> editUser (@PathVariable Long id,@Valid @RequestBody User user)
    {return userService.updateUser(id, user.getName(), user.getEmail())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());}

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        return userService.deleteUser(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();}
}
