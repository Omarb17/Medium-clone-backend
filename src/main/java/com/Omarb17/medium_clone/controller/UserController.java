package com.Omarb17.medium_clone.controller;
import com.Omarb17.medium_clone.mapper.UserMapper;
import com.Omarb17.medium_clone.model.dto.request.UserRequestDto;
import com.Omarb17.medium_clone.model.dto.response.UserResponseDto;
import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @Autowired
    public UserController(
            UserService userService,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
       return userService.getUserById(id)
               .map(userMapper::toResponseDto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Add a new user")
    @PostMapping
    public ResponseEntity<UserResponseDto> addNewUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userService.addNewUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponseDto(user));
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
