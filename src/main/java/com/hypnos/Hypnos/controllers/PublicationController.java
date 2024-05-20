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

    @GetMapping("/text/{text}")
    public ResponseEntity<List<Publication>> findPublicationByText(@PathVariable String text) {
        List<Publication> publications = publicationService.findPublicationByText(text);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Publication>> findPublicationByUserId(@PathVariable Long id) {
        List<Publication> publications = publicationService.findPublicationByUserId(id);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/user/{alias}")
    public ResponseEntity<List<Publication>> findPublicationByUserAlias(@PathVariable String alias) {
        List<Publication> publications = publicationService.findPublicationByUserAlias(alias);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Publication>> findPublicationsByCategoryIds(@RequestParam List<Long> categoryIds) {
        List<Publication> publications = publicationService.findPublicationsByCategoryIds(categoryIds);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/liked/{userId}")
    public ResponseEntity<List<Publication>> findLikedPublicationsByUserId(@PathVariable Long userId) {
        List<Publication> publications = publicationService.findLikedPublicationsByUserId(userId);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Publication>> getRandomPublications(@RequestParam int page, @RequestParam int size) {
        List<Publication> publications = publicationService.getRandomPublications(page, size);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<List<Publication>> getPublicationsFromFollowedUsersOrderByCreatedAtDesc(@PathVariable Long userId) {
        List<Publication> publications = publicationService.getPublicationsFromFollowedUsersOrderByCreatedAtDesc(userId);
        return ResponseEntity.ok(publications);
    }
}
