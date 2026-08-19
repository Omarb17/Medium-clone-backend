package com.Omarb17.medium_clone.mapper;

import com.Omarb17.medium_clone.model.dto.request.UserRequestDto;
import com.Omarb17.medium_clone.model.dto.response.UserResponseDto;
import com.Omarb17.medium_clone.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}