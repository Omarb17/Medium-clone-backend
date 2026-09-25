package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.mapper.CommentMapper;
import com.Omarb17.medium_clone.model.dto.response.CommentResponseDto;
import com.Omarb17.medium_clone.model.entity.Comment;
import com.Omarb17.medium_clone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponseDto> getAllCommentsByStoryId(Long storyId) {
        return commentRepository.findByStoryId(storyId)
                .stream() .map(commentMapper::toResponseDto) .toList();
    }

    public Comment addNewComment(Comment comment) { return commentRepository.save(comment);}

    public boolean deleteComment(Long id) {
        return commentRepository.findById(id)
            .map(comment -> {
                commentRepository.deleteById(id);
                return true;
            })
            .orElse(false);}

    public Optional<Comment> updateComment(Long id, String content) {
        return commentRepository.findById(id).map(comment -> {
            comment.setContent(content);
            return commentRepository.save(comment);
        });
    }

}
