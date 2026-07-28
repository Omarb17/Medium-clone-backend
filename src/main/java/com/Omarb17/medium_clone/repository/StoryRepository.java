package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long>  {
}
