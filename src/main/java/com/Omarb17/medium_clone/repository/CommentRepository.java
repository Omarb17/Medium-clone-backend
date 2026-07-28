package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
}
