package com.example.catchclone.review.service;

import com.example.catchclone.common.dto.PageDto;
import com.example.catchclone.common.dto.StatusResponseDto;
import com.example.catchclone.reservation.dao.ReservationRepository;
import com.example.catchclone.reservation.service.interfaces.UserReservationService;
import com.example.catchclone.review.dao.ReviewPictureRepository;
import com.example.catchclone.review.dao.ReviewRepository;
import com.example.catchclone.review.dto.ReviewRequestDto;
import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.review.dto.UpdateReviewRequestDto;
import com.example.catchclone.review.entity.Review;
import com.example.catchclone.review.entity.ReviewPicture;
import com.example.catchclone.review.service.interfaces.ReviewService;
import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.service.StoreService;
import com.example.catchclone.user.entity.User;
import com.example.catchclone.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final UserService userService;
  private final ReservationRepository reservationRepository;

  private final StoreService storeService;
  private final ReviewPictureRepository reviewPictureRepository;


  private final UserReservationService userReservationService;
  @Override
  @Transactional
  public StatusResponseDto addReview(User user, ReviewRequestDto reviewRequestDto,Long storeId) {

    //방문예약이 정상처리(status=v)인지 확인
    userReservationService.findReservationStatusVById(reviewRequestDto.reservationId());
    //이미 해당 방문건에 대한 리뷰가 있는지 조회
    reviewRepository.findByReviewByReservationId(reviewRequestDto.reservationId());

    //리뷰 별점을 상점 정보에 업데이트 하기위한 구문
    Store store = storeService.findStoreByStoreId(storeId);
    Float storeTotalSumRate = store.getTotalRate();
    Float reviewTotalRate = reviewRequestDto.tasteRating() + reviewRequestDto.serviceRating() + reviewRequestDto.atmosphereRating();


    System.out.println(reviewTotalRate);



    Review review = new Review(user.getId(),storeId,reviewRequestDto);
    reviewRepository.saveAndFlush(review);

    Long reviewCount = reviewRepository.getReviewCountByStoreId(storeId);
    Float storeTotalRate = ((storeTotalSumRate+reviewTotalRate)/3)/reviewCount;
    store.updateStarRate(storeTotalRate,reviewTotalRate);

    List<String> urlList = reviewRequestDto.pictureUrl();
    saveReviewPicture(review,urlList);

    return new StatusResponseDto(201,"Created");
  }

  @Override
  @Transactional(readOnly = true)
  public ReviewResponseDto getReview(Long reviewId) {
    return reviewRepository.responseReviewDtoByReviewId(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 id입니다!")
    );
  }

  @Override
  @Transactional
  public StatusResponseDto updateReview(Long reviewId, Long userId,
      UpdateReviewRequestDto updateReviewRequestDto) {
    Review review = findReviewByReviewId(reviewId);
    User user = userService.findUserByUserId(userId);

    if (!review.isWriter(user, review))
      return new StatusResponseDto(400, "Bad Request");

    review.update(updateReviewRequestDto);

    return new StatusResponseDto(200, "Success");
  }

  @Override
  @Transactional
  public StatusResponseDto deleteReview(User user, Long reviewId) {
    Review review = findReviewByReviewId(reviewId);

    if (!review.isWriter(user, review))
      return new StatusResponseDto(400, "Bad Request");

    reviewRepository.deleteById(reviewId);

    return new StatusResponseDto(204, "No Content");
  }

  @Override
  @Transactional
  public Review findReviewByReviewId(Long reviewId) {
    return reviewRepository.findById(reviewId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 Id입니다")
    );
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ReviewResponseDto> getStoreReviews(Long storeId, PageDto pageDto) {
    return reviewRepository.findAllByStoreId(storeId,pageDto);
  }

  private void saveReviewPicture(Review review,List<String> urlList) {
    urlList.stream()
        .map(s -> ReviewPicture.builder().pictureUrl(s).review(review).build())
        .forEach(reviewPictureRepository::save);
  }

}
