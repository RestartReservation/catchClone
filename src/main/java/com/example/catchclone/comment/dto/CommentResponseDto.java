package com.example.catchclone.comment.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    Long id;
    Long userId;
    String commentContent;
    String nickName;
    LocalDateTime createdAt;
    Long parentId;
    Integer layer;
    Long likeCount;
}

