package com.example.catchclone.like.entity.reviewLike;

import com.example.catchclone.review.entity.Review;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.util.TimeStamped;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReviewLike extends TimeStamped {
  //컬럼
  @EmbeddedId
  private ReviewLikeId reviewLikeId;

  //생성자
  @Builder
  public ReviewLike(User user, Review review) {
    this.user = user;
    this.review= review;
    this.reviewLikeId = getReviewLikeId(user, review);
  }

  private static ReviewLikeId getReviewLikeId(User user, Review review) {
    ReviewLikeId id = new ReviewLikeId();
    id.setUserId(user.getId());
    id.setReviewId(review.getId());
    return id;
  }

  //연관관계
  @ManyToOne
  @MapsId("user_id")
  User user;

  @ManyToOne
  @MapsId("Review_id")
  Review review;

}
