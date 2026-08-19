package com.Omarb17.medium_clone.repository;

import com.Omarb17.medium_clone.model.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long>  {
}
