package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.services.publication.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id, @RequestParam String alias) {
        publicationService.deleteById(id, alias);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication, @AuthenticationPrincipal User user) {
        publication.setUser(user);
        Publication createdPublication = publicationService.save(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPublication);
    }

    @PatchMapping("/{id}/categories")
    public ResponseEntity<Publication> updateCategories(@PathVariable Long id, @RequestBody List<Long> categoryIds, @RequestParam Long userId) {
        Publication updatedPublication = publicationService.updateCategories(id, categoryIds, userId);
        return ResponseEntity.ok(updatedPublication);
    }
}
