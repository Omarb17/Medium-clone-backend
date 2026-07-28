package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.entity.Comment;
import com.Omarb17.medium_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
