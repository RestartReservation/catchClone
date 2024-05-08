package com.example.catchclone.review.dao;

import static com.example.catchclone.like.entity.reviewLike.QReviewLike.reviewLike;
import static com.example.catchclone.review.entity.QReview.review;

import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Optional<Review> findByReviewByReservationId(Long reservationId) {

    Optional<Review> rs = Optional.ofNullable( jpaQueryFactory.select(review)
        .from(review)
        .where(review.reservationId.eq(reservationId))
        .fetchOne()
);
    if(rs.isPresent()) throw new IllegalArgumentException("이미 해당 예약에 대한 리뷰가 존재합니다!");
    return rs;

  }

  @Override
  @Transactional(readOnly = true)
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
                    , ExpressionUtils.as
                        (
                            JPAExpressions.select(Wildcard.count)
                                .from(reviewLike)
                                .leftJoin(reviewLike.review)
                                .where(reviewLikeEqByReviewId(reviewId)),
                            "likeCount"))
            )
            .from(review)
            .where(review.id.eq(reviewId))
            .fetchFirst());
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> findAllByStoreId(Long storeId) {
     return
        jpaQueryFactory
            .select(
                Projections.bean(
                    ReviewResponseDto.class
                    , review.id.as("reviewId")
                    , review.reviewTitle
                    , review.reviewContent
                    , review.tasteRating
                    , review.atmosphereRating
                    , review.serviceRating
                    , review.totalRating
                    , review.createdAt
                    , ExpressionUtils.as
                        (
                            JPAExpressions.select(Wildcard.count)
                                .from(reviewLike)
                                .leftJoin(reviewLike.review)
                                .where(reviewLikeEqByStoreId(storeId)),
                            "likeCount"))
            )
            .from(review)
            .where(review.storeId.eq(storeId))
            .orderBy(review.createdAt.desc())
            .fetch();
  }

  private BooleanExpression reviewLikeEqByReviewId(Long reviewId) {
    return Objects.nonNull(reviewId) ? reviewLike.review.id.eq(reviewId) : null;
  }
  private BooleanExpression reviewLikeEqByStoreId(Long storeId) {
    return Objects.nonNull(storeId) ? reviewLike.review.storeId.eq(storeId) : null;
  }
}
