package com.example.catchclone.comment.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(Long CommentId, Long userId, String commentContents, String nickname, LocalDateTime createdAt, Long likeCount) {

}

