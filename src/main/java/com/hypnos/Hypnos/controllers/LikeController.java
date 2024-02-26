package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.Likes.LikeRequestDto;
import com.hypnos.Hypnos.dtos.Likes.LikeResponseDto;
import com.hypnos.Hypnos.mappers.LikeMapper;
import com.hypnos.Hypnos.models.PublicationLike;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.services.Like.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/likes")
@Slf4j
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final LikeMapper likeMapper;

    @GetMapping("")
    public ResponseEntity<List<LikeResponseDto>> getAllLikes(
    ) {
        log.info("getAllLikes");

        return ResponseEntity.ok(
                likeMapper.toResponse(likeService.getAllLikes())
        );
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<LikeResponseDto>> getLikesByUser(
            @PathVariable User user
    ){
        return ResponseEntity.ok(
                likeMapper.toResponse(likeService.findLikesByUser(user))
        );
    }

//    @GetMapping("/by-tobuy/{toBuy}")
//    public ResponseEntity<List<LikeResponseDto>> getLikesByToBuy(
//            @PathVariable boolean toBuy
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findLikesByToBuy(toBuy))
//        );
//    }

    //    @GetMapping("/by-tocheck/{toCheck}")
//    public ResponseEntity<List<LikeResponseDto>> getLikesByToCheck(
//            @PathVariable boolean toCheck
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findLikesByToCheck(toCheck))
//        );
//    }
    @GetMapping("/{id}")
    public ResponseEntity<LikeResponseDto> getLikeById(
            @PathVariable UUID id
    ) {
        log.info("getLikeById");
        return ResponseEntity.ok(
                likeMapper.toResponse(likeService.findLikeById(id))
        );
    }

    @PostMapping
    public ResponseEntity<LikeResponseDto> postLike(
            @RequestBody LikeRequestDto likeRequestDto
    ) {
        log.info("addLike");
        PublicationLike likeSaved = likeService.addLike(likeMapper.toModel(likeRequestDto));
        return ResponseEntity.created(null).body(
                likeMapper.toResponse(likeSaved)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikeResponseDto> putLike(
            @PathVariable UUID id,
            @RequestBody LikeRequestDto likeRequestDto
    ) {
        log.info("putLike");
        PublicationLike likeUpdated = likeService.updateLike(id, likeMapper.toModel(likeRequestDto));
        return ResponseEntity.ok(
                likeMapper.toResponse(likeUpdated)
        );
    }

//    @PatchMapping("/stock/{id}")
//    public ResponseEntity<LikeResponseDto> setStock(
//            @PathVariable Long id,
//            @RequestParam Double stock
//
//    ) {
//        LocalDateTime stockTimestamp = LocalDateTime.now();
//        log.info("setStock");
//        Like likeUpdated = publicationService.setStock(id, stock, stockTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(likeUpdated)
//        );
//    }

//    @PatchMapping("/tobuy/{id}")
//    public ResponseEntity<LikeResponseDto> setToBuy(
//            @PathVariable Long id,
//            @RequestParam Boolean toBuy
//    ) {
//        LocalDateTime toBuyTimestamp = LocalDateTime.now();
//        log.info("setToBuy");
//        Like likeUpdated = publicationService.setToBuy(id, toBuy, toBuyTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(likeUpdated)
//        );
//    }

//    @PatchMapping("/tocheck/{id}")
//    public ResponseEntity<LikeResponseDto> setToCheck(
//            @PathVariable Long id,
//            @RequestParam Boolean toCheck
//
//    ) {
//        LocalDateTime toCheckTimestamp = LocalDateTime.now();
//        log.info("setToCheck");
//        Like likeUpdated = publicationService.setToCheck(id, toCheck, toCheckTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(likeUpdated)
//        );
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LikeResponseDto> deleteLike(
            @PathVariable UUID id
    ) {
        log.info("deleteLike");
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<LikeResponseDto> patchLike(
            @PathVariable UUID id,
            @RequestBody LikeRequestDto likeRequestDto
    ) {
        log.info("patchGeneralLike");

        PublicationLike likePatched = likeService.patch(id, likeMapper.toModel(likeRequestDto));

        return ResponseEntity.ok(likeMapper.toResponse(likePatched));
    }

//    @GetMapping("/max-stock")
//    public ResponseEntity<List<LikeResponseDto>> getLikesByMaxStock(
//            @RequestParam Double maxStock
//    ) {
//        log.info("getLikesByMaxStock");
//
//        if (maxStock < 0) return ResponseEntity.badRequest().build();
//
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findByStockLessThanEqual(maxStock))
//        );
//    }
}
