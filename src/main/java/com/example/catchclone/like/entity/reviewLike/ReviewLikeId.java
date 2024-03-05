package com.example.catchclone.like.entity.reviewLike;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReviewLikeId implements Serializable {

  @Serial
  private static final long serialVersionUID = 912813899396736106L;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "review_id")
  private Long reviewId;

  @Builder
  public ReviewLikeId(Long userId,Long reviewId){
    this.userId = userId;
    this.reviewId = reviewId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewLikeId reviewLikeId = (ReviewLikeId) o;
    return Objects.equals(getUserId(), reviewLikeId.getUserId()) && Objects.equals(getReviewId(),
        reviewLikeId.getReviewId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getReviewId());
  }
}
