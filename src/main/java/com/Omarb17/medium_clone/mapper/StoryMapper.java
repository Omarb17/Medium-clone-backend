package com.Omarb17.medium_clone.mapper;

import com.Omarb17.medium_clone.model.dto.response.StoryResponseDto;
import com.Omarb17.medium_clone.model.dto.response.UserResponseDto;
import com.Omarb17.medium_clone.model.entity.Story;
import com.Omarb17.medium_clone.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper {


    public StoryResponseDto toResponseDto(Story story) {
        return new StoryResponseDto(
                story.getId(),
                story.getTitle(),
                story.getText(),
                story.getUser().getId()
        );
    }
}
