package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.Publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.Publication.PublicationResponseDto;
import com.hypnos.Hypnos.dtos.User.UserResponseDto;
import com.hypnos.Hypnos.mappers.PublicationMapper;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.services.Publication.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController //Marca una clase como un controlador REST, donde cada método maneja una solicitud HTTP
// y devuelve datos serializados directamente al cuerpo de la respuesta HTTP
@RequestMapping("/api/publications")
//Define la ruta base para todos los métodos dentro de un controlador REST
@Slf4j
//genera automáticamente un logger llamado log en la clase marcada, que puede ser utilizado para registrar mensajes de registro.
@RequiredArgsConstructor
// genera automáticamente un constructor que inicializa todos los campos final y anotados con @NonNull,
// facilitando la inyección de dependencias en Spring.


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

    @GetMapping("/by-title/{title}")
    public ResponseEntity<List<PublicationResponseDto>> getPublicationsByTitle(
            @PathVariable String title
    ) {
        log.info("getPublicationsByName");

        return ResponseEntity.ok(publicationMapper.toResponse(publicationService.findPublicationByTitle(title))
        );
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<PublicationResponseDto>> getPublicationsByUser(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(
                publicationMapper.toResponse(publicationService.findPublicationsByUser(userId))
        );
    }

//    @GetMapping("/by-tobuy/{toBuy}")
//    public ResponseEntity<List<PublicationResponseDto>> getPublicationsByToBuy(
//            @PathVariable boolean toBuy
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findPublicationsByToBuy(toBuy))
//        );
//    }

//    @GetMapping("/by-tocheck/{toCheck}")
//    public ResponseEntity<List<PublicationResponseDto>> getPublicationsByToCheck(
//            @PathVariable boolean toCheck
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findPublicationsByToCheck(toCheck))
//        );
//    }
    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponseDto> getPublicationById(
            @PathVariable UUID id
    ) {
        log.info("getPublicationById");
        return ResponseEntity.ok(
                publicationMapper.toResponse(publicationService.findPublicationById(id))
        );
    }

    @PostMapping
    public ResponseEntity<PublicationResponseDto> postPublication(
            @RequestBody PublicationRequestDto publicationRequestDto
    ) {
        log.info("addPublication");
        Publication publicationSaved = publicationService.addPublication(publicationMapper.toModel(publicationRequestDto));
        return ResponseEntity.created(null).body(
                publicationMapper.toResponse(publicationSaved)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponseDto> putPublication(
            @PathVariable UUID id,
            @RequestBody PublicationRequestDto publicationRequestDto
    ) {
        log.info("putPublication");
        Publication updatedPublication = publicationService.updatePublication(id, publicationMapper.toModel(publicationRequestDto));
        return ResponseEntity.ok(
                publicationMapper.toResponse(updatedPublication)
        );
    }

//    @PatchMapping("/stock/{id}")
//    public ResponseEntity<PublicationResponseDto> setStock(
//            @PathVariable Long id,
//            @RequestParam Double stock
//
//    ) {
//        LocalDateTime stockTimestamp = LocalDateTime.now();
//        log.info("setStock");
//        Publication publicationUpdated = publicationService.setStock(id, stock, stockTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationUpdated)
//        );
//    }

//    @PatchMapping("/tobuy/{id}")
//    public ResponseEntity<PublicationResponseDto> setToBuy(
//            @PathVariable Long id,
//            @RequestParam Boolean toBuy
//    ) {
//        LocalDateTime toBuyTimestamp = LocalDateTime.now();
//        log.info("setToBuy");
//        Publication publicationUpdated = publicationService.setToBuy(id, toBuy, toBuyTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationUpdated)
//        );
//    }

//    @PatchMapping("/tocheck/{id}")
//    public ResponseEntity<PublicationResponseDto> setToCheck(
//            @PathVariable Long id,
//            @RequestParam Boolean toCheck
//
//    ) {
//        LocalDateTime toCheckTimestamp = LocalDateTime.now();
//        log.info("setToCheck");
//        Publication publicationUpdated = publicationService.setToCheck(id, toCheck, toCheckTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationUpdated)
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublicationResponseDto> deletePublication(
            @PathVariable UUID id
    ) {
        log.info("deletePublication");
        publicationService.deletePublication(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<PublicationResponseDto> patchPublication(
            @PathVariable UUID id,
            @RequestBody PublicationRequestDto publicationRequestDto
    ) {
        log.info("patchGeneralPublication");

        Publication publicationPatched = publicationService.patch(id, publicationMapper.toModel(publicationRequestDto));

        return ResponseEntity.ok(publicationMapper.toResponse(publicationPatched));
    }

//    @GetMapping("/max-stock")
//    public ResponseEntity<List<PublicationResponseDto>> getPublicationsByMaxStock(
//            @RequestParam Double maxStock
//    ) {
//        log.info("getPublicationsByMaxStock");
//
//        if (maxStock < 0) return ResponseEntity.badRequest().build();
//
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findByStockLessThanEqual(maxStock))
//        );
//    }
}
