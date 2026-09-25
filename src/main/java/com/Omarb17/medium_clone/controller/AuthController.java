package com.Omarb17.medium_clone.controller;

import com.Omarb17.medium_clone.model.dto.request.LoginRequestDto;
import com.Omarb17.medium_clone.model.dto.request.RegisterRequestDto;
import com.Omarb17.medium_clone.model.dto.response.AuthResponseDto;
import com.Omarb17.medium_clone.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        return ResponseEntity.ok(
                authService.login(request)
        );
    }


}
