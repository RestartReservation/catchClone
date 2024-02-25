package com.example.catchclone.review.service;

import com.example.catchclone.review.dao.ReviewRepository;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.review.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  @Override
  @Transactional
  public StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId) {
    Review review = Review.builder()
        .build();

    reviewRepository.save(review);

    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional(readOnly = true)
  public ReviewResponseDto getReview(Long reviewId) {
    Review review =  reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
    return new ReviewResponseDto(review.getId(), review.getReviewContent(), review.getTasteRating(),
        review.getAtmosphereRating(), review.getServiceRating(), review.getTotalRating(),review.getCreatedAt());
  }
}
