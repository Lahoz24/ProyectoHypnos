package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.mappers.PublicationMapper;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.services.publication.PublicationService;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PublicationResponseDto> getPublicationById(@PathVariable Long id) {
        try {
            Publication publication = publicationService.findById(id);
            if (publication != null) {
                PublicationResponseDto responseDto = publicationMapper.toResponse(publication);
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error while fetching publication by ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PublicationResponseDto> updatePublication(
            @PathVariable Long id,
            @RequestBody PublicationRequestDto publicationRequestDto
    ) {
        try {
            log.info("Updating publication with ID: {}", id);
            Publication existingPublication = publicationService.findById(id);
            if (existingPublication == null) {
                return ResponseEntity.notFound().build();
            }
            existingPublication.setText(publicationRequestDto.getText());
            Publication updatedPublication = publicationService.save(existingPublication);
            PublicationResponseDto responseDto = publicationMapper.toResponse(updatedPublication);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error while updating publication with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public ResponseEntity<List<Object[]>> getRandomPublications() {
        List<Object[]> publications = publicationService.findRandomPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<List<Publication>> getPublicationsFromFollowedUsersOrderByCreatedAtDesc(@PathVariable Long userId) {
        List<Publication> publications = publicationService.getPublicationsFromFollowedUsersOrderByCreatedAtDesc(userId);
        return ResponseEntity.ok(publications);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PublicationSimpleDto>> getPublicationsByCategoryId(@PathVariable Long categoryId) {
        List<Publication> publications = publicationService.getPublicationsByCategoryId(categoryId);
        List<PublicationSimpleDto> publicationSimpleDtos = publicationMapper.toSimpleDtoList(publications);
        return ResponseEntity.ok(publicationSimpleDtos);
    }
}