package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.Likes.LikeRequestDto;
import com.hypnos.Hypnos.dtos.Likes.LikeResponseDto;
import com.hypnos.Hypnos.models.PublicationLike;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LikeMapper {

    public LikeResponseDto toResponse(PublicationLike like) {
        return new LikeResponseDto(
                like.getUuid(),
                like.getUser(),
                like.getPublication()
        );
    }

    public List<LikeResponseDto> toResponse(List<PublicationLike> likes) {
        return likes.stream()
                .map(this::toResponse)
                .toList();
    }

    public PublicationLike toModel(LikeRequestDto likeRequestDto) {
        return new PublicationLike(
                UUID.randomUUID(),
                likeRequestDto.getPublication(),
                likeRequestDto.getUser()
        );
    }
}
