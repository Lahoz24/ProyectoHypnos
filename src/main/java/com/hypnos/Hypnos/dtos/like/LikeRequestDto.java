package com.hypnos.Hypnos.dtos.like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LikeRequestDto {
    private Long userId;
    private boolean like;
}

