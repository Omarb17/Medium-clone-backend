package com.Omarb17.medium_clone.model.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {


    private Long id;


    private String content;


    private Long storyId;


    private String userId;
}
