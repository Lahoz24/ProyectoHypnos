package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.mappers.PublicationMapper;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.services.publication.PublicationService;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/publications")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;
    private final UserServiceImpl userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id, @RequestParam String alias) {
        publicationService.deleteById(id, alias);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public PublicationResponseDto createPublication(
            @RequestBody PublicationRequestDto publicationRequestDto
    ) {
        try {
            log.info("Creating new publication");
            Publication publicationSaved = publicationMapper.toModel(publicationRequestDto);
            return publicationMapper.toResponse(publicationService.save(publicationSaved));
        } catch (Exception e) {
            log.error("Error while creating publication: {}", e.getMessage());
             throw new RuntimeException("Failed to create publication");
        }
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

    @GetMapping("/user/id/{id}")
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
