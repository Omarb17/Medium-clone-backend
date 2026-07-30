package com.Omarb17.medium_clone.service;

import com.Omarb17.medium_clone.entity.Publication;
import com.Omarb17.medium_clone.entity.Story;
import com.Omarb17.medium_clone.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getAllPublications() {return publicationRepository.findAll();}

    public Optional<Publication> getPublicationById(Long id) {return publicationRepository.findById(id);}

    public Publication addNewPublication(Publication publication) {return publicationRepository.save(publication);}
}
