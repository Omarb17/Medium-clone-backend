package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findByStoryId(Long storyId);
}
