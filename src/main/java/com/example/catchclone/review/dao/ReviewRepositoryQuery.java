package com.example.catchclone.review.dao;

import com.example.catchclone.common.dto.PageDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryQuery {
  Optional<ReviewResponseDto> responseReviewDtoByReviewId(Long reviewId);

  Optional<Review> findByReviewByReservationId(Long reservationId);

  Page<ReviewResponseDto> findAllByStoreId(Long storeId, PageDto pageDto);
}
