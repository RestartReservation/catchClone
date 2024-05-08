package com.example.catchclone.review.dao;

import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import java.util.Optional;

public interface ReviewRepositoryQuery {
  Optional<ReviewResponseDto> responseReviewDtoByReviewId(Long reviewId);

  Optional<Review> findByReviewByReservationId(Long reservationId);
}
