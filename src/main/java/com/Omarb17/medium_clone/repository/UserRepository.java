package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
