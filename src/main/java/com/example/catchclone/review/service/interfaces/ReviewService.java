package com.example.catchclone.review.service.interfaces;

import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.user.entity.User;

public interface ReviewService {
  StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId);
  //단일 리뷰 불러오기
  ReviewResponseDto getReview(Long reviewId);
}
