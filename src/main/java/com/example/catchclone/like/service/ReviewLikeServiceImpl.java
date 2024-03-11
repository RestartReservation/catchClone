package com.example.catchclone.like.service;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.like.dao.reviewLike.ReviewLikeRepository;
import com.example.catchclone.like.entity.reviewLike.ReviewLike;
import com.example.catchclone.like.entity.reviewLike.ReviewLikeId;
import com.example.catchclone.like.service.interfaces.ReviewLikeService;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.review.service.interfaces.ReviewService;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewLikeServiceImpl implements ReviewLikeService {
  private final ReviewLikeRepository reviewLikeRepository;
  private final UserService userService;
  private final ReviewService reviewService;

  @Override
  @Transactional
  public StatusResponseDto requestReviewLike(Long userId, Long reviewId) {
    ReviewLikeId reviewLikeId = ReviewLikeId.builder()
        .userId(userId)
        .reviewId(reviewId)
        .build();

    if(reviewLikeRepository.existByReviewLikeId(reviewLikeId)) reviewLikeRepository.deleteByReviewLikeId(reviewLikeId);

    else{
      User user = userService.findUserByUserId(userId);
      Review review = reviewService.findReviewByReviewId(reviewId);
      reviewLikeRepository.save(new ReviewLike(user,review));
    }

    return new StatusResponseDto(200,"OK");
  }
}
