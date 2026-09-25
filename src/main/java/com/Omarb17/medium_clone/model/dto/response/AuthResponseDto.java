package com.Omarb17.medium_clone.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {


    private String token;
    private UserResponseDto user;


}
