package com.example.catchclone.review.dao;

import static com.example.catchclone.review.entity.QReview.review;

import com.example.catchclone.review.dto.ReviewResponseDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional
  public Optional<ReviewResponseDto> responseReviewDtoByReviewId(Long reviewId) {
    return Optional.ofNullable(
        jpaQueryFactory
            .select(
                Projections.bean(
                    ReviewResponseDto.class
                    , review.id
                    , review.reviewContent
                    , review.totalRating
                    , review.atmosphereRating
                    , review.serviceRating
                    , review.totalRating
                    , review.createdAt
            )
            )
            .from(review)
            .where(review.id.eq(reviewId))
            .fetchFirst());
  }
}
