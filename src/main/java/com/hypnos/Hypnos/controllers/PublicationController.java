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

    @PostMapping("/create")
    public PublicationResponseDto createPublication(@RequestBody PublicationRequestDto publicationRequestDto) {
        try {
            log.info("Creating new publication");
            Publication publicationSaved = publicationMapper.toModel(publicationRequestDto);
            return publicationMapper.toResponse(publicationService.save(publicationSaved));
        } catch (Exception e) {
            log.error("Error while creating publication: {}", e.getMessage());
            throw new RuntimeException("Failed to create publication");
        }
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
    public ResponseEntity<List<Publication>> findPublicationsByCategoryIds(@RequestParam Long categoryIds) {
        List<Publication> publications = publicationService.findPublicationByCategoryId(categoryIds);
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Publication>> getRandomPublications() {
        List<Publication> publications = publicationService.findRandomPublications();
        return ResponseEntity.ok(publications);
    }


}