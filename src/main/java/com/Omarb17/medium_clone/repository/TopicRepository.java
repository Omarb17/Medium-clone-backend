package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long>  {
}
