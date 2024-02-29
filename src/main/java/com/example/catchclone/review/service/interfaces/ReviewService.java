package com.example.catchclone.review.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.dto.UpdateReviewRequestDto;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.security.UserDetailsImpl;
import com.example.catchclone.user.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReviewService {
  StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId);
  //단일 리뷰 불러오기
  ReviewResponseDto getReview(Long reviewId);
  //리뷰 업데이트
  StatusResponseDto updateReview( Long reviewId,Long userId,UpdateReviewRequestDto updateReviewRequestDto);
  StatusResponseDto deleteReview(User user, Long reviewId);
  Review findReviewByReviewId(Long reviewId);
}
