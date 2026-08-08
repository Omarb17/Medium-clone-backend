package com.Omarb17.medium_clone.controller;
import com.Omarb17.medium_clone.entity.Publication;
import com.Omarb17.medium_clone.service.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }


    @Operation(
            summary = "Get all publications",
            description = "Retrieves a list of all publications"
    )
    @GetMapping
    public List<Publication> getPublicationsList() {
        return publicationService.getAllPublications();
    }

    @Operation(
            summary = "Get publication by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById (@PathVariable Long id) {
        return publicationService.getPublicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new publication")
    @PostMapping
    public ResponseEntity<Publication> addNewPublication(@Valid @RequestBody Publication publication) {
        Publication savedPublication = publicationService.addNewPublication(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublication);
    }

}
