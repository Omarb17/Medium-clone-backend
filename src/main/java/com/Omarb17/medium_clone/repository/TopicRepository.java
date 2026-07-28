package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long>  {
}
