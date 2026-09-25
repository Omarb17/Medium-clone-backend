package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.model.dto.request.LoginRequestDto;
import com.Omarb17.medium_clone.model.dto.request.RegisterRequestDto;
import com.Omarb17.medium_clone.model.dto.response.AuthResponseDto;
import com.Omarb17.medium_clone.model.dto.response.UserResponseDto;
import com.Omarb17.medium_clone.model.entity.User;
import com.Omarb17.medium_clone.repository.UserRepository;
import com.Omarb17.medium_clone.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDto register(RegisterRequestDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(
                savedUser.getId(),
                savedUser.getEmail()
        );

        UserResponseDto userResponse = new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

        return new AuthResponseDto(token, userResponse);
    }

    public AuthResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail()
        );

        UserResponseDto userResponse = new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

        return new AuthResponseDto(token, userResponse);
    }

}
