package com.hypnos.Hypnos.controllers;


import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.services.publication.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publication")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;


    @PatchMapping("/{userId}/{publicationId}/categories")
    public ResponseEntity<Publication> updatePublicationCategories(
            @PathVariable Long userId,
            @PathVariable Long publicationId,
            @RequestBody List<Long> categoryIds
    ) {
        Publication updatedPublication = publicationService.updateCategories(publicationId, categoryIds, userId);
        return ResponseEntity.ok(updatedPublication);
    }

}

