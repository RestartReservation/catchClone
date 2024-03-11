package com.example.catchclone.review.dao;

import com.example.catchclone.review.dto.ReviewResponseDto;
import java.util.Optional;

public interface ReviewRepositoryQuery {
  Optional<ReviewResponseDto> responseReviewDtoByReviewId(Long reviewId);
}