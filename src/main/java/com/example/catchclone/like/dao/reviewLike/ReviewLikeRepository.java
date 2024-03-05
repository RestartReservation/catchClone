package com.example.catchclone.like.dao.reviewLike;

import com.example.catchclone.like.entity.reviewLike.ReviewLike;
import com.example.catchclone.like.entity.reviewLike.ReviewLikeId;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReviewLikeRepository extends Repository<ReviewLike,ReviewLikeId>,ReviewLikeRepositoryQuery {
  void save(ReviewLike reviewLike);
  Optional<ReviewLike> findByReviewIdAndUserId(Long reviewId, Long userId);
  void deleteByReviewLikeId(ReviewLikeId reviewLikeId);
}
