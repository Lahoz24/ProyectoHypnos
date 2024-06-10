package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.PublicationMapper;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.services.publication.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publications")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;

    @GetMapping("")
    public ResponseEntity<List<PublicationResponseDto>> getAllPublications(
    ) {
        log.info("getAllPublications");

        return ResponseEntity.ok(
                publicationMapper.toResponse(publicationService.findAll())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublicationResponseDto> deletePublication(@PathVariable Long id) {
        log.info("deletePublication");
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
    public ResponseEntity<PublicationResponseDto> createPublication(@RequestBody PublicationRequestDto publicationRequestDto) {
        try {
            log.info("Creating new publication");
            Publication publicationSaved = publicationService.create(publicationRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(publicationMapper.toResponse(publicationSaved));
        } catch (Exception e) {
            log.error("Error while creating publication: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<PublicationResponseDto>> findPublicationByText(@PathVariable String text) {
        List<Publication> publications = publicationService.findPublicationByText(text);
        List<PublicationResponseDto> responseDtos = publications.stream()
                .map(publicationMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/user/id/{id}")
    public ResponseEntity<List<PublicationResponseDto>> findPublicationByUserId(@PathVariable Long id) {
        List<Publication> publications = publicationService.findPublicationByUserId(id);
        List<PublicationResponseDto> responseDtos = publications.stream()
                .map(publicationMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/user/{alias}")
    public ResponseEntity<List<PublicationResponseDto>> findPublicationByUserAlias(@PathVariable String alias) {
        List<Publication> publications = publicationService.findPublicationByUserAlias(alias);
        List<PublicationResponseDto> responseDtos = publications.stream()
                .map(publicationMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<PublicationResponseDto>> findPublicationsByCategoryIds(@RequestParam List<Long> categoryIds) {
        List<Publication> publications = publicationService.findPublicationsByCategoryIds(categoryIds);
        List<PublicationResponseDto> responseDtos = publications.stream()
                .map(publicationMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/random")
    public ResponseEntity<List<PublicationResponseDto>> getRandomPublications() {
        List<Publication> publications = publicationService.findRandomPublications();
        List<PublicationResponseDto> responseDtos = publications.stream()
                .map(publicationMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<Long> getLikesCount(@PathVariable Long id) {
        try {
            long likesCount = publicationService.getLikesCount(id);
            return ResponseEntity.ok(likesCount);
        } catch (Exception e) {
            log.error("Error while fetching likes count for publication ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
