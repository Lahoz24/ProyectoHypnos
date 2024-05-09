package com.hypnos.Hypnos.dtos.comment;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentRequestDto {
    private String text;
    private User user;
    private Publication publication;
}
