package com.example.catchclone.like.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;

public interface ReviewLikeService {
  StatusResponseDto requestReviewLike(Long userId, Long reviewId);
}
