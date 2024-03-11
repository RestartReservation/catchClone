package com.example.catchclone.like.dao.reviewLike;

import com.example.catchclone.like.entity.reviewLike.ReviewLikeId;

public interface ReviewLikeRepositoryQuery {
  boolean existByReviewLikeId(ReviewLikeId reviewLikeId);
}
