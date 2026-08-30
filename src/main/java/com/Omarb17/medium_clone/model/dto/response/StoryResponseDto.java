package com.Omarb17.medium_clone.model.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoryResponseDto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 70, message = "Title must be between 2 and 70 characters")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

//    private int readingTime;
//
//    private int likeCount;

    private Long userId;
}
