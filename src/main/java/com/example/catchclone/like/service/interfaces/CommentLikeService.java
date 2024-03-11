package com.example.catchclone.like.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;

public interface CommentLikeService {
  StatusResponseDto requestCommentLike(Long userId, Long commentId);
}
