package com.example.catchclone.comment.dao;

import com.example.catchclone.comment.dto.CommentResponseDto;
import java.util.List;
import java.util.Optional;

public interface CommentRepositoryQuery {
  Optional<CommentResponseDto> responseCommentDtoByCommentId(Long commentId);
  List<CommentResponseDto> findCommentsByUserId(Long userId);
  List<CommentResponseDto> findCommentsByReviewId(Long reviewId);
}
