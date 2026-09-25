package com.Omarb17.medium_clone.mapper;

import com.Omarb17.medium_clone.model.dto.response.CommentResponseDto;
import com.Omarb17.medium_clone.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDto toResponseDto(Comment comment) {

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUser().getName() );
    }
}
