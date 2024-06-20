package com.example.catchclone.review.dao;

import static com.example.catchclone.like.entity.reviewLike.QReviewLike.reviewLike;
import static com.example.catchclone.review.entity.QReview.review;
import static com.example.catchclone.review.entity.QReviewPicture.reviewPicture;
import static com.example.catchclone.user.entity.QUser.user;

import com.example.catchclone.review.dto.ReviewPictureDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.entity.Review;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
    // ReviewResponseDto 조회
    Optional<ReviewResponseDto> reviewResponseDto = Optional.ofNullable(
        jpaQueryFactory
            .select(
                Projections.bean(
                    ReviewResponseDto.class,
                    review.id.as("reviewId"),
                    review.reviewTitle,
                    review.reviewContent,
                    review.tasteRating,
                    review.atmosphereRating,
                    review.serviceRating,
                    review.totalRating,
                    review.createdAt,
                    user.nickName.as("userNickName"),
                    user.profileUrl.as("userProfileUrl"),
                    ExpressionUtils.as(
                        JPAExpressions.select(Wildcard.count)
                            .from(reviewLike)
                            .leftJoin(reviewLike.review)
                            .where(reviewLike.review.id.eq(reviewId)),
                        "likeCount"
                    )
                )
            )
            .from(review)
            .where(review.id.eq(reviewId))
            .leftJoin(user).on(review.userId.eq(user.id))
            .fetchFirst()
    );

    // ReviewPicture 조회
    List<String> reviewPictures = jpaQueryFactory
        .select(
            reviewPicture.pictureUrl.as("reviewPictureUrl")
        )
        .from(reviewPicture)
        .join(reviewPicture.review, review)
        .where(review.id.eq(reviewId))
        .fetch();

    // ReviewPicture를 ReviewResponseDto에 추가
    if (reviewResponseDto.isPresent()) {
      ReviewResponseDto dto = reviewResponseDto.get();
      List<ReviewPictureDto> pictureDtos = reviewPictures.stream()
          .map(pictureUrl -> {
            ReviewPictureDto pictureDto = new ReviewPictureDto();
            pictureDto.setReviewPictureUrl(pictureUrl);
            return pictureDto;
          })
          .collect(Collectors.toList());
      dto.setReviewPictures(pictureDtos);
    }

    return reviewResponseDto;
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> findAllByStoreId(Long storeId) {

  //List<PictureUrl>을 set하기 위한 dto조회
   List<ReviewResponseDto> reviewResponseDtos =  jpaQueryFactory
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
                    , user.nickName.as("userNickName")
                    , user.profileUrl.as("userProfileUrl")
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
            .leftJoin(user).on(review.userId.eq(user.id))
            .orderBy(review.createdAt.desc())
            .fetch();

   //매개변수로 주어진 storeId를 가지고 있는 review의 reviewPicture 모두 조회
    List<Tuple> reviewPictures = jpaQueryFactory
        .select(
            review.id.as("reviewId"),
            reviewPicture.pictureUrl.as("reviewPictureUrl")
        )
        .from(reviewPicture)
        .join(reviewPicture.review, review)
        .where(review.storeId.eq(storeId))
        .fetch();


    //Mapping을 위한 Map생성
    Map<Long, ReviewResponseDto> reviewDtoMap = reviewResponseDtos.stream()
        .collect(Collectors.toMap(ReviewResponseDto::getReviewId, dto -> dto));

    //조건(같은 reviewId)에 부합하는 reviewPicture객체를 List만들어 reviewResponseDto에 set
    for (Tuple tuple : reviewPictures) {
      Long reviewId = tuple.get(0, Long.class);
      String reviewPictureUrl = tuple.get(1, String.class);

      ReviewResponseDto reviewResponseDto = reviewDtoMap.get(reviewId);

      if (reviewResponseDto != null) {
        //reviewPictureDto set
        ReviewPictureDto reviewPictureDto = new ReviewPictureDto();
        reviewPictureDto.setReviewPictureUrl(reviewPictureUrl);

        //생성된 reviewPictureList가 없으면 List set
        if (reviewResponseDto.getReviewPictures() == null) {
          reviewResponseDto.setReviewPictures(new ArrayList<>());
        }

        //reviewPictureList에 새로운 reviewPictureDto추가
        reviewResponseDto.getReviewPictures().add(reviewPictureDto);
      }
    }


    //처리된 ReviewResponseDto Map List로 변환해 반환
    return new ArrayList<>(reviewDtoMap.values());
  }

  private BooleanExpression reviewLikeEqByReviewId(Long reviewId) {
    return Objects.nonNull(reviewId) ? reviewLike.review.id.eq(reviewId) : null;
  }
  private BooleanExpression reviewLikeEqByStoreId(Long storeId) {
    return Objects.nonNull(storeId) ? reviewLike.review.storeId.eq(storeId) : null;
  }
}
