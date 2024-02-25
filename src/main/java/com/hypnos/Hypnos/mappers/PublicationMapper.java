package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.Publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.Publication.PublicationResponseDto;
import com.hypnos.Hypnos.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
//es una forma de marcar una clase como un componente de la aplicación y permitir
// que Spring la administre de manera automática como parte del contexto de la aplicación
public class PublicationMapper {

    public PublicationResponseDto toResponse(Publication publication) {
        return new PublicationResponseDto(
                publication.getUuid(),
                publication.getTitle(),
                publication.getDescription(),
                publication.getContainsImage(),
                publication.getContainsAudio(),
                publication.getContainsText(),
                publication.getPublicationDate(),
                publication.getUser(),
                publication.getComments()
        );
    }

    public List<PublicationResponseDto> toResponse(List<Publication> publications) {
        return publications.stream()
                .map(this::toResponse)
                .toList();
    }

    public Publication toModel(PublicationRequestDto publicationRequestDto) {
        return new Publication(
                UUID.randomUUID(),
                publicationRequestDto.getTitle(),
                publicationRequestDto.getDescription(),
                publicationRequestDto.getContainsImage(),
                publicationRequestDto.getContainsText(),
                publicationRequestDto.getContainsAudio(),
                LocalDateTime.now(),
                publicationRequestDto.getUser(),
                null
        );
    }

}
