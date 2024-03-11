package com.example.catchclone.like.dao.reviewLike;

import static com.example.catchclone.like.entity.reviewLike.QReviewLike.reviewLike;

import com.example.catchclone.like.entity.reviewLike.ReviewLikeId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewLikeRepositoryQueryImpl implements ReviewLikeRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public boolean existByReviewLikeId(ReviewLikeId reviewLikeId) {
    return jpaQueryFactory.from(reviewLike).where(reviewLike.reviewLikeId.eq(reviewLikeId))
        .select(reviewLike.reviewLikeId)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }
}
